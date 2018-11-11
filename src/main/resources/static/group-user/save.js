var Comparator = Comparator || {};

Comparator.SaveGroupUser = (function() {
    function SaveGroupUser() {
        this.userLang = navigator.language || navigator.userLanguage;
        this.usersMultiSelect = 'usersMultiSelect';
        this.userSelectFooter = 'userSelectFooter';
        this.qtdOptionsUsersSelect = $('#' + this.usersMultiSelect + ' option').length - 1;
        this.pagination = false;
    };

    SaveGroupUser.prototype.iniciar = function() {
        onInternationalizedMessages.call(this);
        onMultiSelectUsers.call(this);
    };

    function onInternationalizedMessages() {
        if(this.userLang == 'pt-BR') {
            this.available = 'Disponíveis';
            this.of = 'de';
            this.users = 'usuários';
            this.searchSelectionUsers = 'Pesquisar usuários disponíveis';
            this.searchSelectedUsers = 'Pesquisar usuários selecionados';
        }
        if(this.userLang == 'en-US') {
            this.available = 'Available';
            this.of = 'of';
            this.users = 'users';
            this.searchSelectionUsers = 'Search for available users';
            this.searchSelectedUsers = 'Search for selected users';
        }
    };

    function onMultiSelectUsers() {
        these = this;
        $('#'+this.usersMultiSelect).multiSelect({
            selectableFooter: "<div class='custom-header' id='userSelectFooter'>" + this.available + " 0 " + this.of + " " + this.qtdOptionsUsersSelect + " " + this.users + " </div>",
            selectableHeader: "<input type='text' id='searchSelected' class='search-input' autocomplete='on' placeholder='" + this.searchSelectionUsers + "'>",
            selectionHeader: "<input type='text' class='search-input' autocomplete='on' placeholder='" + this.searchSelectedUsers + "'>",
            afterInit: function(ms){
                var msSelectable = this.$selectableUl;
                var that = this,
                $selectableSearch = that.$selectableUl.prev(),
                $selectionSearch = that.$selectionUl.prev(),
                selectableSearchString = '#'+that.$container.attr('id')+' .ms-elem-selectable:not(.ms-selected)',
                selectionSearchString = '#'+that.$container.attr('id')+' .ms-elem-selection.ms-selected';
                that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
                .on('keydown', function(e){
                    if (e.which === 40){
                        that.$selectableUl.focus();
                        return false;
                    }
                    setTimeout(function(){
                        if(qs1.matchedResultsCount == 0) {
                            var url = '/usuario/ajax/buscar/page=';
                            var inputSearch = document.getElementById("searchSelected").value;
                            if(inputSearch.length >= 2) {
                                onSearchAjax.call(this, url, these.usersMultiSelect, inputSearch, null, "0");
                            }
                            return false;
                        }
                    },300);
                    setTimeout(function(){
                        qs1.cache();
                        $('#'+these.userSelectFooter).text(these.available + ' ' + qs1.matchedResultsCount + ' ' + these.of + ' ' + $('#' + these.usersMultiSelect + ' option').length + ' ' + these.users);
                    }, 300);
                });
                var qs1 = this.qs1;
                msSelectable.on('scroll', function() {
                    if($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
                        var url = '/usuario/ajax/buscar/page=';
                        this.pagination = true;
                        this.nextPage = parseInt($('#' + these.usersMultiSelect).attr('data-page')) + 1;
                        this.hasNextPage = ($('#' + these.usersMultiSelect).attr('data-last') == 'false');
                        if(this.hasNextPage) {
                            onSearchAjax.call(this, url, these.usersMultiSelect, "", null, this.nextPage);
                        }
                        that.qs1.cache();
                        $('#'+these.userSelectFooter).text(these.available + ' ' + qs1.matchedResultsCount + ' ' + these.of + ' ' + $('#' + these.usersMultiSelect + ' option').length + ' ' + these.users);

                        return false;
                    }
                });
                that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
                .on('keydown', function(e){
                    if (e.which == 40){
                        that.$selectionUl.focus();
                        this.qs1.cache();
                        return false;
                    }
                });
                },
                afterSelect: function(values){
                    var that = this;
                    if(values == '000') {
                        this.pagination = true;
                        var url = '/usuario/ajax/buscar/page=';
                        onSearchAjax.call(this, url, these.usersMultiSelect, "", values, "0");
                    };
                    that.qs1.cache();
                    that.qs2.cache();
                    $('#'+these.userSelectFooter).text(these.available + ' ' + that.qs1.matchedResultsCount + ' ' + these.of + ' ' + $('#' + these.usersMultiSelect + ' option').length + ' ' + these.users);
                },
                afterDeselect: function(){
                    this.qs1.cache();
                    this.qs2.cache();
                    $('#'+these.userSelectFooter).text(these.available + ' ' + this.qs1.matchedResultsCount + ' ' + these.of + ' ' + $('#' + these.usersMultiSelect + ' option').length + ' ' + these.users);
                }
        });
    };

    function onSearchAjax(urlSearch, select, inputSearch, values, page) {
        this.urlSearchPage = urlSearch + page;
        $.ajax({
            url: this.urlSearchPage,
            method: 'GET',
            headers: {
                'name': inputSearch
            },
            contentType: 'application/json',
            async: false,
            error: onErrorSearchAjax.bind(this, select),
            success: onSuccessSearchAjax.bind(this, select, values)
        });
    };

    function onErrorSearchAjax() {
        //console.log(this);
    };

    function onSuccessSearchAjax(select, values, obj) {
        if(values == '000' && $('#'+select).attr('data-page') == -1) {
            $("#"+select+" option[value='" + values + "']").remove();
            $("#"+select+" option:not(:selected)").remove();
            $('#'+select).multiSelect('refresh');

        }
        for (i = 0; i < obj.objects.length; i++){
            $('#'+select).multiSelect('addOption', { value: obj.objects[i].data, text: obj.objects[i].value});
        };
        if(this.pagination) {
            $('#'+select).attr('data-page', obj.number);
            $('#'+select).attr('data-last', obj.last);

            this.pagination = false;
        }
    };

return SaveGroupUser;

}());

$(function() {
    var saveGroupUser = new Comparator.SaveGroupUser();
	saveGroupUser.iniciar();
});