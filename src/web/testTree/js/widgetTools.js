  var zNodes = [{
      id: 1,
      pId: 0,
      name: "北京"
  }, {
      id: 2,
      pId: 0,
      name: "天津"
  }, {
      id: 3,
      pId: 0,
      name: "上海"
  }, {
      id: 6,
      pId: 0,
      name: "重庆"
  }, {
      id: 4,
      pId: 0,
      name: "河北省",
      open: true,
      nocheck: true
  }, {
      id: 41,
      pId: 4,
      name: "石家庄"
  }, {
      id: 42,
      pId: 4,
      name: "保定"
  }, {
      id: 43,
      pId: 4,
      name: "邯郸"
  }, {
      id: 44,
      pId: 4,
      name: "承德"
  }, {
      id: 5,
      pId: 0,
      name: "广东省",
      open: true,
      nocheck: true
  }, {
      id: 51,
      pId: 5,
      name: "广州"
  }, {
      id: 52,
      pId: 5,
      name: "深圳"
  }, {
      id: 53,
      pId: 5,
      name: "东莞"
  }, {
      id: 54,
      pId: 5,
      name: "佛山"
  }, {
      id: 6,
      pId: 0,
      name: "福建省",
      open: true,
      nocheck: true
  }, {
      id: 61,
      pId: 6,
      name: "福州"
  }, {
      id: 62,
      pId: 6,
      name: "厦门"
  }, {
      id: 63,
      pId: 6,
      name: "泉州"
  }, {
      id: 64,
      pId: 6,
      name: "三明"
  }];

  (function($) {
      if (typeof $.fn.myWidgets == "undefined") {
          $.fn.myWidgets = function(options) {
              var $this = $(this);
              if ($this.length > 0 && options) {
                  var domObj = $(this).get(0);
                  var defaults = {
                      widgetType: 'default',
                      label: '<span style="color:red">未填写</span>',
                      data: [],
                      id: domObj.id
                  };

                  var opts = $.extend(defaults, options);
                  return widegets(opts);

              } else {
                  alert('无法获取元素！');
                  return false;
              }
          };
      } else {
          alert('控件初始化无效！');
          return false;
      }

      var widegets = function(options) {
          var that = this;
          var fun_searchCombox;
          var widgetTypeMap = {
              searchCombox: "searchCombox"
          }
          var widegetMap = {};

          widegetMap[widgetTypeMap.searchCombox] = function() {
              var configMap = new Object();
              var jqueryMap = new Object();
              var pMap = new Object();
              var idMap = new Object();

              configMap.zNodes = options.data;
              var setting = {
                  check: {
                      enable: true,
                      chkboxType: {
                          "Y": "s",
                          "N": "s"
                      },
                      chkStyle: "checkbox"
                  },
                  view: {
                      dblClickExpand: false
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

              configMap.setting = setting;
              var widgetValues = new Array();


              idMap.id = options.id;
              idMap.SearchCheckbox = idMap.id + "SearchCheckbox";
              idMap.idSearchInput = idMap.id + "SearchInput";
              idMap.idTree = idMap.id + "Tree";
              idMap.idTreeSearch = idMap.id + "TreeSearch";


              var classMap = new Object();
              var classMapSelector = new Object();
              classMap.s = classMapSelector;

              classMap.labelLoc = idMap.id + " labelLoc";
              classMap.s.labelLoc = "." + idMap.id + ".labelLoc";

              classMap.inputLoc = idMap.id + " inputLoc";
              classMap.s.inputLoc = "." + idMap.id + ".inputLoc"; //".widgetId.inputLoc"

              classMap.menuBtnLoc = idMap.id + " menuBtnLoc";
              classMap.s.menuBtnLoc = "." + idMap.id + ".menuBtnLoc";

              classMap.zTreeContent = idMap.id + " zTreeContent";
              classMap.s.zTreeContent = "." + idMap.id + ".zTreeContent"; //classMap.s.zTreeContent

              classMap.selectionChoice = idMap.id + " selection-choice";
              classMap.s.selectionChoice = "." + idMap.id + ".selection-choice"; //".widgetId.selection-choice"

              classMap.selectionChoiceRemove = idMap.id + " selection-choice-remove";
              classMap.s.selectionChoiceRemove = "." + idMap.id + ".selection-choice-remove";

              configMap.getMainWidgetHtml = function() {
                  var eleHtml =
                      '<div id="' + idMap.SearchCheckbox + '">' +
                      '     <ul id="' + idMap.id + '" class="list">' +
                      '         <li class="' + classMap.labelLoc + '">' + options.label + ':</li>' +
                      '         <li class="' + classMap.inputLoc + '"></li>' +
                      '         <li class="' + classMap.menuBtnLoc + '">extend</li>' +
                      '     </ul>' +
                      '     <div class="' + classMap.zTreeContent + '" style="display:none; position: absolute;">' +
                      '         <input id="' + idMap.idSearchInput + '" type="text" class="empty">' +
                      '         <ul id="' + idMap.idTree + '" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>' +
                      '         <ul id="' + idMap.idTreeSearch + '" class="ztree" style="margin-top:0; width:180px; height: 300px;"></ul>' +
                      '     </div>' +
                      '</div>';
                  return eleHtml;
              };

              configMap.getCheckedHtml = function(nodeId, mc) {
                  var eleHtml =
                      '<div class="' + classMap.selectionChoice + '">' +
                      '    <span id="scr_' + nodeId + '" class="' + classMap.selectionChoiceRemove + '">x</span>' +
                      '    <span>' + mc + '</span>' +
                      '</div>';
                  return eleHtml;
              };

              function zTreeOnCheck(event, treeId, treeNode, clickFlag) {

                  var $widgetIdTree = $.fn.zTree.getZTreeObj(idMap.idTree);
                  var $widgetIdTreeSearch = $.fn.zTree.getZTreeObj(idMap.idTreeSearch);

                  var isMainTree = !($(event.target).parents("#" + idMap.idTreeSearch).length > 0);
                  if (isMainTree) {
                      var nodes = $widgetIdTree.getCheckedNodes(true);
                      var leafNodeArray = [];
                      leafNodeArray = getAllChildrenNodes(nodes, leafNodeArray);
                      var content = "";
                      widgetValues.length = 0;
                      for (var i = 0; i < leafNodeArray.length; i++) {
                          var node = leafNodeArray[i];
                          widgetValues.push(node.tId);
                          content += configMap.getCheckedHtml(node.id, node.name);
                      }
                      var $inputDiv = $(classMap.s.inputLoc);
                      $inputDiv.html(content);
                      $(classMap.s.selectionChoiceRemove).on("click", removeSelection);
                  } else {
                      var isChecked = treeNode.checked;
                      treeNode = $widgetIdTree.getNodeByParam("id", treeNode.id, null);
                      $widgetIdTree.checkNode(treeNode, isChecked, true, true);
                  }
                  return false;

              }

              function getAllChildrenNodes(nodeArray, leafNodeArray) {
                  for (var i = 0; i < nodeArray.length; i++) {
                      if (!nodeArray[i].isParent) {
                          leafNodeArray.push(nodeArray[i]);
                      }
                  }
                  return leafNodeArray;
              }

              function removeSelection(event) {
                  var id = event.target.id;
                  id = parseInt(id.match(/scr_(\S*)/)[1]);
                  var $zTreeObj = $.fn.zTree.getZTreeObj(idMap.idTree);
                  var keyType = "id";
                  var node = $zTreeObj.getNodeByParam(keyType, id);
                  $zTreeObj.checkNode(node, false, true, true);

              }

              var isShowMenu = true;

              function showMenu() {
                  $(".zTreeContent").fadeOut("fast");
                  pMap.$input = $(classMap.s.inputLoc);
                  var inputOffset = pMap.$input.offset();
                  var $zTreeContent = $(classMap.s.zTreeContent);
                  $zTreeContent.css({
                      left: inputOffset.left + "px",
                      top: inputOffset.top + pMap.$input.outerHeight() + "px"
                  }).slideDown("fast");
                  eventSwitch(isShowMenu);
                  isShowMenu = false;
                  return isShowMenu;
              };

              function hideMenu() {
                  $(classMap.s.zTreeContent).fadeOut("fast");
                  eventSwitch(isShowMenu);
                  isShowMenu = true;
                  return false;
              };

              function eventSwitch(isShowMenu) {
                  if (isShowMenu) {
                      $(classMap.s.inputLoc).unbind('click', toggleMenu);
                      $("body").bind("click", toggleMenu);
                  } else {
                      $(classMap.s.inputLoc).on('click', toggleMenu);
                      $("body").unbind("click", toggleMenu);
                  }
                  return false;
              }

              function toggleMenu(event) {
                  var $currentTarget = $(event.target);
                  var isSwitch = (($currentTarget.parents(".zTreeContent").length > 0) || ($currentTarget.parents(".selection-choice").length > 0));
                  if (isSwitch) {
                      return false;
                  }
                  return isShowMenu ? showMenu() : hideMenu();
              }

              function focusKey(e) {
                  if (jqueryMap.$searchInput.hasClass("empty")) {
                      jqueryMap.$searchInput.removeClass("empty");
                  }
                  return false;
              };

              function blurKey(e) {
                  if (jqueryMap.$searchInput.get(0).value === "") {
                      jqueryMap.$searchInput.addClass("empty");
                  }
                  return false;
              };

              function searchNode(e) {
                  var value = $.trim(jqueryMap.$searchInput.get(0).value);
                  if (value.length > 0) {
                      var zTree = $.fn.zTree.getZTreeObj(idMap.idTree);
                      var nodeArray = zTree.getNodesByParamFuzzy("name", value);
                      $("#" + idMap.idTree).hide();
                      $("#" + idMap.idTreeSearch).show();
                      updateResultTree(nodeArray);
                  } else {
                      $("#" + idMap.idTree).show();
                      $("#" + idMap.idTreeSearch).hide();
                  }
                  return false;
              }

              function updateResultTree(nodeArray) {
                  $.fn.zTree.init($("#" + idMap.idTreeSearch), setting,
                      nodeArray);
              }


              function init() {
                  var $targetDiv = $("#" + idMap.id);
                  if ($targetDiv.length > 0) {
                      var targetDivObj = $targetDiv[0];
                      var htmlText = configMap.getMainWidgetHtml();
                      targetDivObj.outerHTML = htmlText;
                  } else {
                      alert("页面标签未定义！")
                      return false;
                  }
                  jqueryMap.$searchInput = $("#" + idMap.idSearchInput);
                  $.fn.zTree.init($("#" + idMap.idTree),
                      setting, configMap.zNodes);
                  $.fn.zTree.init($("#" + idMap.idTreeSearch), setting,
                      configMap.zNodes);
                  $("#" + idMap.idTreeSearch).hide();
                  jqueryMap.$searchInput.on("focus", focusKey).on("blur", blurKey)
                      .on("propertychange", searchNode).on("input", searchNode);
                  $(classMap.s.inputLoc).on('click', toggleMenu);
                  return {
                      values: widgetValues
                  };
              }

              return {
                  init: init
              };
          };

          //
          if (options && options.widgetType && options.widgetType == widgetTypeMap[options.widgetType]) {
              var wideget = widegetMap[options.widgetType]();
              var init = wideget.init()
              return init;
          } else {
              alert("尚无此类控件！");
          }
      }
  })(jQuery);
