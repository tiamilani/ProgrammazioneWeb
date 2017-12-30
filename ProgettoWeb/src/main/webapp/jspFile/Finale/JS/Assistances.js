function openAssistance(elem)
{
    var index = elem.getAttribute("data-idA");
    document.location.href='/ProgettoWeb/AssistenzaController?action=resolve&id=' + index;
};

function saveChangesAssistance(wtd)
{
    if(wtd == 0)
        document.formSaveChanges.action="/ProgettoWeb/AssistenzaController?action=save";
    else if(wtd == 1)
        document.formSaveChanges.action="/ProgettoWeb/AssistenzaController?action=saveAndClose";
    
    document.formSaveChanges.submit();
};