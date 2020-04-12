$(document).ready(function () {
    getMainCategories();
});

function getMainCategories() {
    $.getJSON("http://localhost:8080/category/public/getMainCategories", function (data) {
        var items = [];
        var totalPages = data.totalPages;
        var categories = data.elements
        console.log(data);
        console.log(totalPages);

        for (let i = 0; i < totalPages; i++) {
            let page = i + 1;
            items.push('<li class="page-item"><a class="page-link" href="#" id="page' + page + '">' + page + '</a></li>');
        }
        if (totalPages == 1) {
            $("#nextItem").addClass("disabled");
        }

        $("#previousItem").after(items.join(""));

        if (categories != null) {
            $.each(categories, function (key, val) {
                $("<div />").attr("class", "row justify-content-center align-items-center m-3")
                    .attr("id", "categories" + key).appendTo("#main");
                $("<div />").attr("class", "col-4").attr("id", "imgCategory" + key).appendTo("#categories" + key);
                $("<div />").attr("class", "col-5 list-group").attr("id", "categoryName" + key)
                    .appendTo("#categories" + key);

                if (val.foto != null) {
                    $("<img/>").attr("src", "data:image/png;base64," + val.foto)
                        .attr("class", "img-fluid img-thumbnail rounded float-right")
                        .attr("alt", "img").attr("title", "img").css("maxWidth", "30%").appendTo("#imgCategory" + key);
                } else {
                    $("<img/>").attr("src", "img/box.png").attr("class", "img-fluid img-thumbnail rounded float-right")
                        .attr("alt", "img").attr("title", "img").css("maxWidth", "30%").appendTo("#imgCategory" + key);
                }

                if (val.nombre != null) {
                    $("<a/>").attr("href", "#").attr("class", "list-group-item list-group-item-action")
                        .append(val.nombre).appendTo("#categoryName" + key);
                } else {
                    $("<a/>").attr("href", "#").attr("class", "list-group-item list-group-item-action")
                        .append("none").appendTo("#categoryName" + key);
                }
            });
        }
    });
}