var Comparator = Comparator || {};

Comparator.SaveAccess = (function() {
    function SaveAccess() {
        this.userLang = navigator.language || navigator.userLanguage;
        this.usersMultiSelect = 'usersMultiSelect';
        this.userSelectFooter = 'userSelectFooter';
        this.qtdOptionsUsersSelect = $('#' + this.usersMultiSelect + ' option').length;
        this.qtdSelectableOptions = $('#' + this.usersMultiSelect + ' option:not(:selected)').length;
    };

    SaveAccess.prototype.iniciar = function() {
        console.log(this.qtdSelectableOptions);
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
            selectableOptgroup: true,
            selectableFooter: "<div class='custom-header' id='userSelectFooter'>" + this.available + ' ' + this.qtdSelectableOptions + ' ' + this.of + " " + this.qtdOptionsUsersSelect + " " + this.users + " </div>",
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
                        qs1.cache();
                        $('#'+these.userSelectFooter).text(these.available + ' ' + qs1.matchedResultsCount + ' ' + these.of + ' ' + $('#' + these.usersMultiSelect + ' option').length + ' ' + these.users);
                    }, 300);
                });
                var qs1 = this.qs1;
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

return SaveAccess;

}());

$(function() {
    var saveAccess = new Comparator.SaveAccess();
	saveAccess.iniciar();
});