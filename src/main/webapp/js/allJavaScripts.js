$(document).ready(function () {
    MyCartComponent.bindAll();
});

var MyCartComponent = (function(){
    var Config = {
        //add to cart
        ADD_TO_CART_BUTTON: ".js-add-to-cart-button",
        NO_OF_ITEMES:".js-quantity-class",

        //update
        TOTAL_COST:"#js-place-order",
        UPDATE_CLASS_ITEMS:".js-update-cart-button",
        PRICE_OF_ITEM:".js-price-of-item",
        QUANTITY_TO_UPDATE:".js-quantity-to-update",
        TABLE_CLASS_TO_UPDATE:".js-product-from-cart",

        //place order
        PLACE_ORDER: "#js-place-order",

        //order details
        DETAILS_BUTTON: ".js-show-order-details",

        //common
        MY_CART_VIEW:"#JS-cart-item-count",

        //dropdown category
        DROPDOWN_CATEGORY:".dropbtn-custom"

    };

    var bindAllComponents = function () {
         addToCart();
         updateCart();
         placeOrder();
         showOrderDetails();
         selectCategory();
    }

    var addToCart = function () {
        $(Config.ADD_TO_CART_BUTTON).click(function(event){
            var productId = $(event.target).val();
            //var quantity = $(Config.NO_OF_ITEMES).val();

            var quantity = $(event.target).closest(".grid-item").find(".js-quantity-class").val();
            $.ajax({
                type:"POST",
                data: {
                    quantity : quantity,
                    productId : productId,
                    cartComponent : "addProduct"
                },
                url : "cart",
                success : function(rest){
                    var inputStock=parseInt($(Config.MY_CART_VIEW).text());
                    var totalItems = inputStock+parseInt(quantity);
                    $(Config.MY_CART_VIEW).text(totalItems);
                },
                error : function(resp){
                    alert("FAIL");
                }
            });
        });
    }




    var updateCart = function () {
        $(Config.UPDATE_CLASS_ITEMS).click(function(event){
            var productIdFromCart = $(event.target).val();
            var newQuantity = $(event.target).closest(".js-product-from-cart").find(".js-quantity-to-update").val();

            $.ajax({
                type:"POST",
                data: {
                    newQuantity : newQuantity,
                    productIdFromCart : productIdFromCart,
                    cartComponent : "updateProduct"
                },
                url : "cart",
                success : function(){
                    if(parseInt(newQuantity)==0){
                        deleteItem();
                    }else {
                        updateItem();
                    }
                    updateTotalCost();
                    updateItemCount();
                },
                error : function(){
                    alert("FAIL");
                }
            });
        });
    }

    var placeOrder = function () {
        $(Config.PLACE_ORDER).click(function(event){
            var orderNumber = $(event.target).val();

            $.ajax({
                type:"POST",
                data: {
                    orderNumber : orderNumber,
                    cartComponent : "orderDetails"
                },
                url : "order",
                success : function(){

                },
                error : function(){
                    alert("FAIL");
                }
            });
        });

    }


    var showOrderDetails = function () {
        $(Config.DETAILS_BUTTON).click(function(event){
            var orderItems = $(event.target).val();

            $.ajax({
                type:"POST",
                data: {
                    orderItems : orderItems,
                    orderComponent : "orderDetails"
                },
                url : "order",
                success : function(){
                    window.open("orderDetails.jsp", "_blank", "scrollbars=1,resizable=1,height=300,width=950");

                },
                error : function(){
                    alert("FAIL");
                }
            });
        });

    }

    var selectCategory = function () {
        $(Config.DROPDOWN_CATEGORY).click(function(event){
            var category = $(event.target).text();

            $.ajax({
                type:"POST",
                data: {
                    category : category
                },
                url : "products",
                success : function(){
                    /*window.location.reload();*/
                    window.location.href = "productPage.jsp"
                },
                error : function(){
                    alert("FAIL");
                }
            });
        });

    }





    //TODO ADD IMPLEMENTATION IN NEXT METHODS
    function deleteItem() {

    }

    function updateItem() {

    }

    function updateTotalCost() {

    }

    function updateItemCount() {

    }


    return {
        bindAll: bindAllComponents
    }
})();