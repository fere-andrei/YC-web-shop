$(document).ready(function () {
    MyCartComponent.bindAll();
});

var MyCartComponent = (function(){
    var Config = {
        ADD_TO_CART_BUTTON: ".js-add-to-cart-button",
        NO_OF_ITEMES:".js-quantity-class",
        MY_CART_VIEW:"#JS-cart-item-count"

    };

    var bindAllComponents = function () {
         addToCart();
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
                    productId : productId
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

    return {
        bindAll: bindAllComponents
    }
})();