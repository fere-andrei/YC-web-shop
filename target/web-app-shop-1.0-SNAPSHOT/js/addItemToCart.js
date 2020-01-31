jQuery(document).ready(function($){
    updateCartItemCount();
});
function addItemToCart(itemId) {
    $.ajax({
        url: '/cart/items',
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data: '{"itemId":"' + itemId + '"}"',
        complete: function (responseData, status, xhttp) {
            updateCartItemCount();
        }
    });
}

function updateCartItemCount()
{
    $.ajax ({
        url: '/cart',
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        complete: function(responseData, status, xhttp){
            $('#cart-item-count').text('('+responseData.responseJSON.count+')');
        }
    });
}

