var Comparator = Comparator || {};

Comparator.SwitchParentMenu = (function() {

    function SwitchParentMenu() {
	    this.checkParentMenu = 'CheckParentMenu';
	    this.selectParentMenu = 'parentMenu';
	}

	SwitchParentMenu.prototype.iniciar = function() {
	    var isParentMenu = $('#' + this.checkParentMenu).is(':checked');
        if(isParentMenu) {
            $('#' + this.selectParentMenu).prop('disabled', true);
        } else {
            $('#' + this.selectParentMenu).prop('disabled', false);
        }

        $('#' + this.checkParentMenu).on('change', onCheckParentMenuChange.bind(this));
	};

	function onCheckParentMenuChange() {
	    var isParentMenu = $('#' + this.checkParentMenu).is(':checked');
	    if(isParentMenu) {
            $('#' + this.selectParentMenu).prop('disabled', true);
	    } else {
            $('#' + this.selectParentMenu).prop('disabled', false);
	    }
	};

return SwitchParentMenu;

}());

$(function() {
    var switchParentMenu = new Comparator.SwitchParentMenu();
	switchParentMenu.iniciar();
});