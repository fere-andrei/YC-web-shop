$(document).ready(function () {
    MyCartComponent.bindAll();
});

var MyCartComponent = (function(){
    var Config = {
        ADD_TO_CART_BUTTON: "#add-to-cart-button",
        NO_OF_ITEMES:"#quantity",
        MY_CART_VIEW:"#JS-cart-item-count"
    };

    var bindAllComponents = function () {
         addToCart();
    }

    var addToCart = function () {
        $(Config.ADD_TO_CART_BUTTON).click(function(){
            var productId = $(Config.ADD_TO_CART_BUTTON).val();
            var quantity = $(Config.NO_OF_ITEMES).val();
            $.ajax({
                type:"POST",
                data: {
                    quantity : quantity,
                    productId : productId
                },
                url : "cart",
                success : function(resp){
                    alert(resp);
                    var inputStock=parseInt($(Config.MY_CART_VIEW).text())+quantity;
                    $(Config.MY_CART_VIEW).text(inputStock);
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