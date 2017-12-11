/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var app = (function () {

    getMessages = function () {
        return $.get("/messages", function (data) {
            $("#msgs").empty();
            data.map(function (msg) {
                $(document).ready(function () {
                    $("#msgs").append(msg).append("<br/>");
                });
            });
        });
    };

    getItems = function () {
        return $.get("/items", function (data) {
            $("#itms").empty();
            data.map(function (itm) {
                $(document).ready(function () {
                    $("#itms").append(JSON.stringify(itm)).append("<br/>");
                });
            });
        });
    };

    return {
        update: function () {
            getMessages().then(function () {
                getItems();
            });
        }
    };
})();