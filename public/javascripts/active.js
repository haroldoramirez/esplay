//Créditos Bacon
$(function () {
        $('.navbar-nav li').click(function () {
            $(this).siblings().removeClass('active');
            $(this).addClass('active');
        });
});

var dropdownArray = document.getElementsByClassName("dropdown");
window.addEventListener("click",function(){
    setTimeout(function(){
        for(var c,i=dropdownArray.length; i--;){
            if((c=dropdownArray[i].getAttribute("class")).indexOf("active")>=0&&c.indexOf("open")==-1){
                dropdownArray[i].setAttribute("class",c.replace(" active",""));
            }
        }
    },0);
});

//em jquery
/*$(document).click(function(){
    $(".dropdown.active").not(".open").removeClass("active");
});*/