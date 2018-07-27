package com.eletroinfo.eletroinfo.comparator.notification;

import com.eletroinfo.eletroinfo.comparator.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MessageSourceUtil {

    @Autowired
    private MessageSource message;

    public String getMessage(String codeInternationalization, String fields){

        Object[] values = null;

        //verifica se nao está vazio e se existe mais de um parametro no message properties
        if(!StringUtil.isNullOrEmpty(fields) && fields.contains(",")){

            String[] fieldArray = fields.split(",");
            values = new Object[fieldArray.length];
            int position = 0;

            for(String field : fieldArray){
                values[position] = StringUtil.isNullOrEmpty(field) ? "" : field;
                position += 1;
            }

        }else{
            //para quando tiver apenas um parametro na messagem
            if(!StringUtil.isNullOrEmpty(fields)){
                values = new Object[]{fields};
            }
        }

        //Caso nao exista o codigo nos messages.properties ele mostra o valores passados no fields
        try{
            return message.getMessage(codeInternationalization, values, LocaleContextHolder.getLocale());
        }catch(NoSuchMessageException e){
            return (values == null ? "" : Arrays.toString(values).replace("[", "").replace("]", ""));
        }
    }

    public String getMessage(String codeInternationalization){
        String msg = message.getMessage(codeInternationalization, null, LocaleContextHolder.getLocale());
        return StringUtil.isNullOrEmpty(msg)? codeInternationalization : msg;
    }


}
