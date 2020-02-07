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


        //common
        MY_CART_VIEW:"#JS-cart-item-count"

    };

    var bindAllComponents = function () {
         addToCart();
         updateCart();
    }

    var addToCart = function () {
        $(Config.ADD_TO_CART_BUTTON).click(function(event){
            var productId = $(event.target).val();
            //var quantity = $(Config.NO_OF_ITEMES).val();

            var quantity = $(event.target).closest(".js-product-details").find(".js-quantity-class").val();
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