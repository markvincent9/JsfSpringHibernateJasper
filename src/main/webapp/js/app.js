function handleLoginRequest(xhr, status, args) {
    if(args.validationFailed || !args.loggedIn) {
        PF('loginPanelWv').jq.effect("shake", {times:5}, 100);
    }
    else {
        PF('loginPanelWv').hide();
        $('#loginLink').fadeOut();
    }
}