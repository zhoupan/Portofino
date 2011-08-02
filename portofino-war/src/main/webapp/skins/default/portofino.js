function fixSideBar() {
    $(
        function() {
            var contentNode = $('#content');
            var sideBarNode = $('#sidebar');
            if (contentNode.offsetHeight < sideBarNode.offsetHeight) {
                contentNode.css('min-height', sideBarNode.offsetHeight + 'px')
            }
        }
    )
}

$(function() {
    $("input:submit.portletPageButton, button.portletPageButton").button();
    
    $("input:submit.portletButton, button.portletButton").button();

    $("input:submit.wrench, button.wrench").button({
            icons: {
                primary: "ui-icon-wrench"
            },
            text: false
        });
});

function enablePortletDragAndDrop() {
    $("div.portletContainer").sortable({
        connectWith: "div.portletContainer"
    }).css('padding', '1em 0').css("border", "1px dashed grey").css("margin-bottom", "1em").css("min-height", "10em");
}