var Comparator = Comparator || {};

Comparator.SaveSeller = (function() {
    function SaveSeller() {
        this.accessSystem = "accessSystem";
        this.systemAccess = "system-access"
        this.email = "user\\.email";
        this.login = "user\\.login";
        this.password = "user\\.password";
    };

    SaveSeller.prototype.iniciar = function() {
        $('#' + this.accessSystem).on('change', onAccessSystemChange.bind(this));
    };

    function onAccessSystemChange() {
        this.valueAccessSystem = $('#' + this.accessSystem).is(':checked');

        if(this.valueAccessSystem == true) {
            $('.'+this.systemAccess).show(0);
            $('#'+this.email).prop('disabled',false);
            $('#'+this.login).prop('disabled',false);
            $('#'+this.password).prop('disabled',false);
        };

        if(this.valueAccessSystem == false) {
            $('.'+this.systemAccess).hide(0);
            $('#'+this.email).prop('disabled',true);
            $('#'+this.login).prop('disabled',true);
            $('#'+this.password).prop('disabled',true);
        };
    };

    return SaveSeller;
}());

$(function() {
    var saveSeller = new Comparator.SaveSeller();
  saveSeller.iniciar();
});