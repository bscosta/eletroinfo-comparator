var Comparator = Comparator || {};

Comparator.ProviderQuickRegister = (function()  {
    function ProviderQuickRegister() {
        this.modal = $('#modalQuickRegisterProvider');
        this.btnSave = this.modal.find('.js-modal-register-provider-salvar-btn');
        this.form = this.modal.find('form');
        this.url = this.form.attr('action');
        this.inputNameProvider = $('#providerName');
        this.containerErrorMessage = $('.js-message-quick-register-provider');
    }

    ProviderQuickRegister.prototype.iniciar = function() {
        this.form.on('submit', function(event) { event.preventDefault() });
        this.modal.on('shown.bs.modal', onModalShow.bind(this));
        this.modal.on('hide.bs.modal', onModalClose.bind(this))
        this.btnSave.on('click', onBtnSaveClick.bind(this));
    }

    function onModalShow() {
        this.inputNameProvider.focus();
    }

    function onModalClose() {
        this.inputNameProvider.val('');
        this.containerErrorMessage.addClass('hidden');
        this.form.find('.form-group').removeClass('has-error');
    }

    function onBtnSaveClick() {
        var nameProvider = this.inputNameProvider.val();
        $.ajaxSetup({
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            }
        });
        $.ajax({
            url: this.url,
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ name: nameProvider }),
            error: onErrorSaveProvider.bind(this),
            success: onSaveProvider.bind(this)
        });
    }

    function onErrorSaveProvider(obj) {
        var errorMessage = obj.responseJSON;
        this.containerErrorMessage.removeClass('hidden');
        console.log(errorMessage);
        this.containerErrorMessage.html('<span>' + errorMessage + '</span>');
        this.form.find('.input-name').addClass('has-error');
    }

    function onSaveProvider(provider) {
        var selectProvider = $('#provider');
        selectProvider.append('<option value=' + provider.id + '>' + provider.name + '</option>');
        selectProvider.val(provider.id);
        this.modal.modal('hide');
    }

    return ProviderQuickRegister;

}());

$(function() {
    var providerQuickResgister = new Comparator.ProviderQuickRegister;
    providerQuickResgister.iniciar();
});