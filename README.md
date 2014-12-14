http://romip.ru/ru/collections/by.web-2007.html (сырые данные)

https://yadi.sk/d/sy4qfmK0LKBva (подготовленные)

http://romip.ru/tasks/2008/web2008_adhoc.xml.bz2 (запросы)

http://romip.ru/relevance-tables/2009/web-adhoc/by/and_relevant-minus_table.xml.gz (таблицы релевантности)

Этапы: 

1) строим индексы и сохраняем в файл
        BuildIndex bi = new BuildIndex();
        File[] get_file_array = bi.get_file_in_folder("путь до файлов txt (около 500)");
        bi.create_index(get_file_array);
Сохраняет индексы в отдельном файле
Индексы (без стеммера)
19 GB (около 1.5 миилиона статей)

2)  массив запросов, которые нужно произвести
        ArrayList<String> need_query_array = XmlWork.get_list_query("/Users/Nurislam/Documents/lucene_index_file/data/new/and_relevant-minus_table.xml");
        номер запроса - текст запроса
        HashMap<String, String> hashmap = XmlWork.get_id_query("/Users/Nurislam/Documents/lucene_index_file/data/new/web2008_adhoc.xml");
  
3) выполняем запросы и получаем список документов, релевантных по данным запросам
        ArrayList<ScoreDoc[]> list_doc_wich_rel = Queries.do_query(need_query_array, hashmap);

4) пишем в xml-файлы запросы и документы, которые наша система посчитала релевантными 
        write_xml(list_doc_wich_rel, need_query_array);
  
5) Metrics.class - подсёт метрик

В виду высокого времени выполнения метрики считались на 5 случайно взятых запросах
Метрики (на 5 запросах)
a = 12 (количество документов, найденных системой и релевантных с точки зрения экспертов)
b = 101 (количество документов, найденных системой, но не релевантных с точки зрения экспертов)
c = 26 (количество релевантных документов, не найденных системой)
d = 482 (количество нерелевантных документов, не найденных системой)
 
recall = 0.3157894736842105
 
precision = 0.10619469026548672
 
accuracy = 0.7954911433172303
 
error = 0.20450885668276972
 
F1 =0.15894039735099338
 
Average Precision = 0.5

Выборка по 5 запросов с ответами, заданными изначально и документами, которая наша программа посчитала релевантными для каждого запроса.
https://www.dropbox.com/s/wnlfsilmwlpvvrk/new_past.zip?dl=0
 

 
