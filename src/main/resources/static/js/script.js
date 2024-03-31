console.log("smart contact manager");

const toggleSidebar=()=>{
    if($(".sidebar").is(":visible")){
        $(".sidebar").css("display","none");
        $(".content").css("margin-left","2%");
        $(".footer").css("margin-left","40%")
    }
    else{
        $(".sidebar").css("display","block");
        $(".content").css("margin-left","20%");
        $(".footer").css("margin-left","30%")
    }

};
