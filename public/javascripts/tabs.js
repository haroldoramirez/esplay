function desactive(aba) {
    var classe, pos;
    classe = aba.getAttribute("class").split(" ");
    pos = classe.indexOf("active");
    if(pos>=0)classe.splice(pos,1);
        classe = classe.join(" ");
        aba.setAttribute("class",classe);
}
    window.addEventListener("click",function(event){
    var classe, pai, srcElement;
    if((srcElement=event.srcElement||event.target).parentElement!=null
        && (pai = srcElement.parentElement.parentElement)!=null
        && (classe=pai.getAttribute("class"))!=null
        && classe.split(" ").indexOf("nav-tabs")>=0){
            var name = srcElement.getAttribute("name");
            var aba = document.getElementById(name);
            var all = aba.parentElement.children;
            for(var i=0; i<all.length; i++){
                desactive(all[i]);
            }
            aba.setAttribute("class",aba.getAttribute("class")+" active");
    }
});