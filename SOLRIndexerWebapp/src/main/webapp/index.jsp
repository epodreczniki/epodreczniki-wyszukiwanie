<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Aplikacja indeksujaca</h1>
        <h2>Dodaje kolekcje wraz z modulami do instancji SOLR</h2>
        
        <h3>Dostepne opcje</h3>

        <b>Usuwanie danych:</b>
        <p>
        DELETE clear
        <br/>
        Czysci zawartosc calego repozytorium SOLR (glownego 'epub', autouzupelniania 'epub_ac' oraz wyszukiwania dynamicznego 'epub_ds'
        </p>
        
        <p>
        DELETE clear/main/
        <br/>
        Czysci zawartosc calego repozytorium glownego 'epub' SOLR
        </p>

        <p>
        DELETE clear/autocomplete/
        <br/>
        Czysci zawartosc calego repozytorium autouzupelniania 'epub_ac' SOLR
        </p>

        <p>
        DELETE clear/dynamicsearch/
        <br/>
        Czysci zawartosc calego repozytorium wyszukiwania dynamicznego 'epub_ds' SOLR
        </p>
        
        <b>Indeksowanie danych:</b>
        <p>
        PUT index?colURL=<collection 1 URL>&colURL=<collection 2 URL>...
        <br/>
        Indeksuje dane.
        </p>
    </body>
</html>
