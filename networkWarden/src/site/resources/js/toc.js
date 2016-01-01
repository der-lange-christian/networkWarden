

var toggleButtonTextOpen="table of content";
var toggleButtonTextClose="minimize";

// http://www.jankoatwarpspeed.com/automatically-generate-table-of-contents-using-jquery/
$(document).ready(function() {
    
    $("#bodyColumn").prepend('<div id="toc"><div id="toc_content"></div></div>');
    //$("#toc").prepend('<p>In this article:</p>');
    $("#toc").append("<a id='toggleButton' href=\"javascript:toggle('toc_content')\">" + toggleButtonTextClose + "</a>");

    $("h3, h4, h5, h6").each(function(i) {
        var current = $(this);
        current.attr("id", "title" + i);
        $("#toc_content").append("<a id='link" + i + "' href='#title" +
            i + "' title='" + current.get(0).tagName + "'>" + 
            current.text() + "</a>");
        //console.log(current.html());
        //console.log(current.text());
    });
    
});

function toggle(id){
    var e = document.getElementById(id);
     
    if (e.style.display === "none"){
        $("#toggleButton").text(toggleButtonTextClose);
        e.style.display = "";
    } else {
        $("#toggleButton").text(toggleButtonTextOpen);
        e.style.display = "none";
    }
  }
