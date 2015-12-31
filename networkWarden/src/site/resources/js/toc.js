
$(document).ready(function() {
    
    $("#bodyColumn").prepend('<div id="toc"></div>');
    $("#toc").prepend('<p>In this article:</p>');
    

    $("h1, h2, h3, h4, h5, h6").each(function(i) {
        var current = $(this);
        current.attr("id", "title" + i);
        $("#toc").append("<a id='link" + i + "' href='#title" +
            i + "' title='" + current.attr("tagName") + "'>" + 
            current.text() + "</a><br />");
        //console.log(current.html());
        //console.log(current.text());
    });
    
});


