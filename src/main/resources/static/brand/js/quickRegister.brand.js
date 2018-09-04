var Comparator = Comparator || {};

Comparator.BrandQuickRegister = (function()  {
    function BrandQuickRegister() {
        this.modal = $('#modalQuickRegisterBrand');
        this.btnSave = this.modal.find('.js-modal-register-brand-salvar-btn');
        this.form = this.modal.find('form');
        this.url = this.form.attr('action');
        this.inputNameBrand = $('#brandName');
        this.inputDescriptionBrand = $('#brandDescription');
        this.containerErrorMessage = $('.js-message-quick-register-brand');
    }

    BrandQuickRegister.prototype.iniciar = function() {
        this.form.on('submit', function(event) { event.preventDefault() });
        this.modal.on('shown.bs.modal', onModalShow.bind(this));
        this.modal.on('hide.bs.modal', onModalClose.bind(this))
        this.btnSave.on('click', onBtnSaveClick.bind(this));
    }

    function onModalShow() {
        this.inputNameBrand.focus();
    }

    function onModalClose() {
        this.inputNameBrand.val('');
        this.containerErrorMessage.addClass('hidden');
        this.form.find('.form-group').removeClass('has-error');
    }

    function onBtnSaveClick() {
        var nameBrand = this.inputNameBrand.val();
        var descriptionBrand = this.inputDescriptionBrand.val();
        $.ajax({
            url: this.url,
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ name: nameBrand, description: descriptionBrand }),
            error: onErrorSaveBrand.bind(this),
            success: onSaveBrand.bind(this)
        });
    }

    function onErrorSaveBrand(obj) {
        var errorMessage = obj.responseJSON;
        this.containerErrorMessage.removeClass('hidden');
        console.log(errorMessage);
        this.containerErrorMessage.html('<span>' + errorMessage + '</span>');
        this.form.find('.input-name').addClass('has-error');
    }

    function onSaveBrand(brand) {
        var selectBrand = $('#brand');
        selectBrand.append('<option value=' + brand.id + '>' + brand.name + '</option>');
        selectBrand.val(brand.id);
        this.modal.modal('hide');
    }

    return BrandQuickRegister;

    }());

    $(function() {
    	var brandQuickResgister = new Comparator.BrandQuickRegister;
    	brandQuickResgister.iniciar();
    });