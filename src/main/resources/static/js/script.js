// console.log("smart contact manager");

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


const search = () => {
    // console.log("search.........");
    let query = $("#search-input").val();
    if(query == ""){
        $(".search-result").hide();
    }
    else{
        // console.log(query);
        url=`http://localhost:8181/search/${query}`;
        fetch(url).then((response)=>{
            return response.json();
        }).then((data)=>{
            // console.log(data);
            let search_list=`<div class="list-group">`;
            data.forEach((contact) => { 
                search_list += `<a href='/user/show-details/${contact.contactId}'  class="list-group-item list-group-action">${contact.name}</a>` 
            });
            search_list += `</div>`;
            $(".search-result").html(search_list);
            $(".search-result").show(); 
        });
    }

};

const showDetails = (id) => {
    console.log("Show Details: " + id);
    const urlShow = `http://localhost:8181/user/show-details/${id}`;

    fetch(urlShow, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
            // Add any other headers if needed
        },
        // You can include a body if you need to send data along with the request
        // body: JSON.stringify({ key: 'value' })
    }).then(response => {
        if (response.ok) {
            console.log("Request sent successfully");
        } else {
            console.error("Request failed");
        }
    }).then((data) => {
        console.log("DATA: "+data);    }

    ).catch(error => {
        console.error("Error:", error);
    });
}









// function myFunction(id){
//     console.log("function Called"+id);
// }
// const showDetails = (id)=>{
//             console.log("show Details: "+id);
//             urlShow=`http://localhost:8181/user/show-details/${id}`;

//             fetch(urlShow, {method: 'POST'});
// }






// const showDetails = (id) => {
//     console.log("Show Details: " + id);
//     const urlShow = `http://localhost:8181/user/show-details/${id}`;

//     fetch(urlShow, {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json'
//         }
//     })
//     .then(response => {
//         if (response.ok) {
//             return response.text(); // Assuming the server returns HTML
//         } else {
//             throw new Error('Failed to fetch view');
//         }
//     })
//     .then(html => {
//         // Update the UI with the fetched HTML
//         document.getElementById('contactDetails').innerHTML = html;
//     })
//     .catch(error => {
//         console.error("Error:", error);
//     });
// }



// '/user/show-details/${contact.contactId}'    href='http://localhost:8181/user/show-details/${contact.contactId}'