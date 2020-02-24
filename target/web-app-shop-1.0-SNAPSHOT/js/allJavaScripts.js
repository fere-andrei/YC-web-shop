$(document).ready(function () {
    MyCartComponent.bindAll();
});

var MyCartComponent = (function () {
    var Config = {
        //add to cart
        ADD_TO_CART_BUTTON: ".js-add-to-cart-button",
        NO_OF_ITEMES: ".js-quantity-class",

        //update
        TOTAL_COST: "#js-place-order",
        UPDATE_CLASS_ITEMS: ".js-update-cart-button",
        PRICE_OF_ITEM: ".js-price-of-item",
        QUANTITY_TO_UPDATE: ".js-quantity-to-update",
        TABLE_CLASS_TO_UPDATE: ".js-product-from-cart",
        TOTAL_COST: "#js-total-cost",

        //place order
        PLACE_ORDER: "#js-place-order",

        //order details
        DETAILS_BUTTON: ".js-show-order-details",

        //common
        MY_CART_VIEW: "#JS-cart-item-count",

        //dropdown category
        DROPDOWN_CATEGORY: ".dropbtn-custom"

    };

    var bindAllComponents = function () {
        addToCart();
        updateCart();
        placeOrder();
        showOrderDetails();
        selectCategory();
    }

    var addToCart = function () {

        $(document).on("click", Config.ADD_TO_CART_BUTTON, function (event) {
            var productId = $(event.target).val();
            var quantity = $(event.target).closest(".grid-item").find(".js-quantity-class").val();
            $.ajax({
                type: "POST",
                data: {
                    quantity: quantity,
                    productId: productId,
                    cartComponent: "addProduct"
                },
                url: "cart",
                success: function (rest) {
                    var inputStock = parseInt($(Config.MY_CART_VIEW).text());
                    var totalItems = inputStock + parseInt(quantity);
                    $(Config.MY_CART_VIEW).text(totalItems);
                },
                error: function (resp) {
                    alert("FAIL");
                }
            });
        });
    }


    var updateCart = function () {
        $(Config.UPDATE_CLASS_ITEMS).click(function (event) {
            var event = $(event.target);
            var productIdFromCart = event.val();
            var oldQuantity = event.closest(".js-product-from-cart").find(".js-quantity-to-update")[0].defaultValue;
            var newQuantity = event.closest(".js-product-from-cart").find(".js-quantity-to-update").val();


            var pricePerUnit = event.closest(".js-product-from-cart").find(".js-price-of-item").data("item-price");

            $.ajax({
                type: "POST",
                data: {
                    newQuantity: newQuantity,
                    productIdFromCart: productIdFromCart,
                    cartComponent: "updateProduct"
                },
                url: "cart",
                success: function () {
                    if (parseInt(newQuantity) === 0) {
                        event.closest(".js-product-from-cart").remove()
                    } else {
                        //update price item
                        var finalPriceOfItem = parseFloat(pricePerUnit) * parseInt(newQuantity);
                        event.closest(".js-product-from-cart").find(".js-price-of-item").text(finalPriceOfItem);

                    }
                    updateTotalCost($(Config.PRICE_OF_ITEM));
                    updateItemCount($(Config.QUANTITY_TO_UPDATE));

                },
                error: function () {
                    alert("FAIL");
                }
            });
        });
    }

    var placeOrder = function () {
        $(Config.PLACE_ORDER).click(function (event) {
            var orderNumber = $(event.target).val();

            $.ajax({
                type: "POST",
                data: {
                    orderNumber: orderNumber,
                    cartComponent: "orderDetails"
                },
                url: "order",
                success: function () {
                    if ($(Config.TOTAL_COST).text() === "0.0") {
                        alert("Pleas add products in cart");

                    } else {

                        $(document).ready(function () {
                            document.getElementById("hidden-message").style.left = "50%";
                            document.getElementById("hidden-message").style.visibility = "visible";
                            window.setTimeout(function () {
                                window.location.href = "home"
                            }, 3000);

                        });
                    }
                },
                error: function () {
                    alert("FAIL");
                }
            });
        });

    }


    var showOrderDetails = function () {
        $(Config.DETAILS_BUTTON).click(function (event) {
            var orderItems = $(event.target).val();

            $.ajax({
                type: "POST",
                data: {
                    orderItems: orderItems,
                    orderComponent: "orderDetails"
                },
                url: "order",
                success: function () {
                    window.open("orderDetails.jsp", "_blank", "scrollbars=1,resizable=1,height=300,width=950");

                },
                error: function () {
                    alert("FAIL");
                }
            });
        });

    }

    var selectCategory = function () {
        $(Config.DROPDOWN_CATEGORY).click(function (event) {
            var category = $(event.target).text();

            $.ajax({
                type: "POST",
                data: {
                    category: category
                },
                url: "products",
                success: function () {
                    window.location.href = "productPage.jsp"
                },
                error: function () {
                    alert("FAIL");
                }
            });
        });

    }


    function updateTotalCost(allPrices) {
        let totalCost = 0;
        for (let i = 0; i < allPrices.size(); i++) {
            totalCost += parseFloat(allPrices[i].text);
        }
        $(Config.TOTAL_COST).text(totalCost);
    }

    function updateItemCount(allQuantity) {
        let totalItems = 0;
        for (let i = 0; i < allQuantity.size(); i++) {
            totalItems += parseInt(allQuantity[i].value);
        }
        $(Config.MY_CART_VIEW).text(totalItems);
    }


    return {
        bindAll: bindAllComponents
    }
})();