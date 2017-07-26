var makeWidgetSwjg = function($, elementId) {
	debugger;
    var configMap = {};
    configMap.eleId = elementId;

    /**********************/
    var styleId = {};
    var styleClass = {};

    var selectorId = {};
    var selectorClass = {};

    styleId.widgetSelect = "widget-select-" + configMap.eleId;
    selectorId.widgetSelect = "#widget-select-" + configMap.eleId;

    styleId.widgetSelectTree = "widget-select-tree-" + configMap.eleId;
    selectorId.widgetSelectTree = "#widget-select-tree-" + configMap.eleId;


    styleClass.widgetSwjg = configMap.eleId + " widget-swjg";
    selectorClass.widgetSwjg = "." + configMap.eleId + ".widget-swjg";

    styleClass.widgetLabel = configMap.eleId + " widget-label";
    selectorClass.widgetLabel = "." + configMap.eleId + ".widget-label";

    styleId.widgetTree = "comboTree" + configMap.eleId;
    selectorId.widgetTree = "#comboTree" + configMap.eleId;

    styleClass.widgetSelectTriger = configMap.eleId + " widget-select-triger";
    selectorClass.widgetSelectTriger = "." + configMap.eleId + ".widget-select-triger";

    styleClass.widgetSelectTree = configMap.eleId + " widget-select-tree";
    selectorClass.widgetSelectTree = "." + configMap.eleId + ".widget-select-tree";

    styleClass.selectionBox = configMap.eleId + " selection_box";
    selectorClass.selectionBox = "." + configMap.eleId + ".selection_box";

    styleClass.selectionChoice = configMap.eleId + " selection_choice";
    selectorClass.selectionChoice = "." + configMap.eleId + ".selection_choice";

    styleClass.selectionChoiceRemove = configMap.eleId + " selection_choice_remove";
    selectorClass.selectionChoiceRemove = "." + configMap.eleId + ".selection_choice_remove";
    /*********************/


    var wSwjg = new Object();
    var values = new Array();
    wSwjg.values = values;

    var setting = {
        check: {
            enable: true,
            chkboxType: {
                "Y": "s",
                "N": "s"
            }
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: zTreeOnCheck
        }
    };


    configMap.getMainHtml = function(id, type, labelName) {
        var eleHtml =
            '    <div id="' + configMap.eleId + '">' +
            '        <div class="' + styleClass.widgetSwjg + '">' +
            '            <label class="' + styleClass.widgetLabel + '">' + configMap.labelName + '</label>' +
            '            <ul id="' + styleId.widgetSelect + '" class="' + styleClass.selectionBox + '"> </ul>' +
            '            <span class="' + styleClass.widgetSelectTriger + '" href="#">下拉</span>' +
            '        </div>' +
            '        <div id="' + styleId.widgetSelectTree + '" class="' + styleClass.widgetSelectTree + '"  style="display:none; position: absolute;">' +
            '            <ul id="' + styleId.widgetTree + '" class="' + configMap.eleId + ' ztree" style="margin-top:0; width:200px;">' +
            '            </ul>' +
            '        </div>' +
            '    </div>';
        return eleHtml;
    }

    configMap.getCheckedHtml = function(nodeId, mc) {
        var eleHtml =
            '<li class="' + styleClass.selectionChoice + '">' +
            '    <span id="scr_' + nodeId + '" class="' + styleClass.selectionChoiceRemove + '">×</span>' +
            '    <span>' + mc + '</span>' +
            '</li>';
        return eleHtml;
    }

    var zNodes = [{
        id: 1,
        pId: 0,
        name: "随意勾选 1"
    }, {
        id: 11,
        pId: 1,
        name: "随意勾选 1-1"
    }, {
        id: 111,
        pId: 11,
        name: "随意勾选 1-1-1"
    }, {
        id: 112,
        pId: 11,
        name: "随意勾选 1-1-2"
    }, {
        id: 12,
        pId: 1,
        name: "随意勾选 1-2"
    }, {
        id: 121,
        pId: 12,
        name: "随意勾选 1-2-1"
    }, {
        id: 122,
        pId: 12,
        name: "随意勾选 1-2-2"
    }, {
        id: 2,
        pId: 0,
        name: "随意勾选 2"
    }, {
        id: 21,
        pId: 2,
        name: "随意勾选 2-1"
    }, {
        id: 22,
        pId: 2,
        name: "随意勾选 2-2"
    }, {
        id: 221,
        pId: 22,
        name: "随意勾选 2-2-1"
    }, {
        id: 222,
        pId: 22,
        name: "随意勾选 2-2-2"
    }, {
        id: 23,
        pId: 2,
        name: "随意勾选 2-3"
    }];

    function removeSelection(event) {
        var id = event.toElement.id;
        id = parseInt(id.match(/scr_(\S*)/)[1]);
        var $zTreeObj = $.fn.zTree.getZTreeObj(styleId.widgetTree);
        var keyType = "id";
        var node = $zTreeObj.getNodeByParam(keyType, id);
        $zTreeObj.checkNode(node, false, true, true);

    }

    function zTreeOnCheck(event, treeId, treeNode, clickFlag) {
        //当check发生变化时，检测checked
        //获取选择的节点
        var treeObj = $.fn.zTree.getZTreeObj(styleId.widgetTree);
        var nodes = treeObj.getCheckedNodes(true);
        var leafNodeArray = [];

        leafNodeArray = getAllChildrenNodes(nodes, leafNodeArray);

        var content = "";
        wSwjg.values.length = 0;
        for (var i = 0; i < leafNodeArray.length; i++) {
            var node = leafNodeArray[i];
            wSwjg.values.push(node.tId);
            content += configMap.getCheckedHtml(node.id, node.name);
        }
        var $inputDiv = $(selectorId.widgetSelect);
        $inputDiv.html(content);
        $("." + styleClass.selectionChoiceRemove).on("click", removeSelection);
    };

    function getAllChildrenNodes(nodeArray, leafNodeArray) {
        for (var i = 0; i < nodeArray.length; i++) {
            if (!nodeArray[i].isParent) {
                leafNodeArray.push(nodeArray[i]);
            }
        }
        return leafNodeArray;
    }

    var isShowMenu = true;

    function showMenu() {
        var $wSelectObj = $(selectorId.widgetSelect);
        var wSelectOffset = $wSelectObj.offset();
        $(selectorId.widgetSelect.jTr).css({
            left: wSelectOffset.left + "px",
            top: wSelectOffset.top + $wSelectObj.outerHeight() + "px",
            width: $wSelectObj.width()
        }).slideDown("fast");

        $("body").on("click", onBodyDown);
        $(selectorClass.widgetSwjg).unbind("click", toggleMenu);
        isShowMenu = false;
        $(selectorClass.widgetSelectTriger).html('retract');
        return isShowMenu;
    }

    function hideMenu() {
        $(selectorClass.widgetSelectTriger).html('extend');
        $(selectorId.widgetSelect.jTr).fadeOut("fast");
        isShowMenu = true;
        $(selectorClass.widgetSwjg).on("click", toggleMenu);
        $("body").unbind("click", onBodyDown);
        return false;
    }

    function toggleMenu(event) {
        var $currentTarget = $(event.target);
        var isSwitch = ($currentTarget.parents(selectorClass.selectionChoice).length > 0 || $currentTarget.parents(selectorId.widgetSelect.jTr).length > 0);
        if (isSwitch) {
            return false;
        }
        return isShowMenu ? showMenu() : hideMenu();
    }



    function onBodyDown(event) {
        var $currentTarget = $(event.target);
        debugger
        var isShow = ($currentTarget.parents(selectorClass.selectionChoice).length > 0 || $currentTarget.parents(selectorId.widgetSelect.jTr).length > 0);
        if (!isShow) {
            hideMenu();
        }
        return false;
    }

    var initialize = function() {
        var $a = $("#" + configMap.eleId);
        var domObj = $a[0];
        configMap.eleId = domObj.id;
        configMap.labelName = domObj.innerHTML;
        domObj.outerHTML = configMap.getMainHtml(configMap.eleId);
        $.fn.zTree.init($(selectorId.widgetTree), setting, zNodes);
        var $targetEle = $('body');
        $targetEle.bind("click", toggleMenu);
        return wSwjg;
    }

    return initialize();

}
