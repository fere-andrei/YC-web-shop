$(document).ready(function () {
    MyCartComponent.bindAll();
});

var MyCartComponent = (function(){
    var Config = {
        ADD_TO_CART_BUTTON: "#js-shopping-cart",
        NO_OF_ITEMES:"#stockValue",
        MY_CART_VIEW:"#JS-cart-item-count"
    };

    var bindAllComponents = function () {
         addToCart();
    }

    var addToCart = function () {
        $(Config.ADD_TO_CART_BUTTON).click(function(){
          var inputStock =parseInt($(Config.NO_OF_ITEMES).val());
          inputStock=parseInt($(Config.MY_CART_VIEW).text())+inputStock;
          $(Config.MY_CART_VIEW).text(inputStock);

        });

    }

    return {
        bindAll: bindAllComponents
    }
})();