function openLoadingScreen() {

    if(window.__load.div == null){

        var div = document.createElement("div");
        window.__load.div = div;
        div.style.position = "fixed";
        div.style.top = "0px";
        div.style.left = "0px";
        div.style.zIndex = "2147483647";
        div.style.right = "0px";
        div.style.bottom = "0px";
        div.style.opacity = "0";
        div.style.backgroundColor = "rgba(0,0,0,0.08)";

        var table = document.createElement("table"),
        tr = document.createElement("tr"),
        td = document.createElement("td");

        //td.innerHTML = "Carregando...";
        //td.style.color = "#fff";
        td.style.fontFamily = "Arial";
        td.style.fontSize = "30px";

        div.appendChild(table);
        table.appendChild(tr);
        tr.appendChild(td);

        table.style.height = "100%";
        table.style.marginLeft = "auto";
        table.style.marginRight = "auto";

        document.body.appendChild(div);

    }

    $(div).animate({
        opacity: 1
    },window.__load.fadeDuration);

}

function closeLoadingScreen() {
    $(window.__load.div).animate({
        opacity: 0
    },window.__load.fadeDuration,function(){
        $(this).remove();
        window.__load.div = null;
    });
}

window.__load = {
    fadeDuration: 175,
    counter: 0,
    ini: function(){
        if(this.counter==0)openLoadingScreen();
        this.counter ++;
    },
    end: function(){
        window.__load.counter--;
        if(window.__load.counter==0)closeLoadingScreen();
    },
};

var bk = XMLHttpRequest.prototype.send;
XMLHttpRequest.prototype.send = function(a,b,c,d,e,f,g,h,i,j,k){
    window.__load.ini();
    var self = this;
    self.bk = bk;
    var s, s2, p;
    for(att in self)
        if(typeof self[att] == "function" && att.substring(0,2)=="on"){
            self[att+"bk"] = self[att];
            eval("self[att] = function(){"
            +    "window.__load.end();"
            +    "this."+att+"bk();"
            +"}");
        }
    self.bk(a,b,c,d,e,f,g,h,i,j,k);
}
//Créditos Bacon