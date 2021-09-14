# Web Lab2
### To-DO 
```
not implemented yet
```
- Fix area check for rect and polygon
- Make no reload for sumbit requests

### Done
```
not done yet
```
- ~~Check if Kotlin can be used in JSP~~: [branch for testing](https://github.com/ulyanovskk/weblab2/tree/kotlin-jsp-test)
- ~~Migrate to c tag (c:forEach) or declare funs in a [class](https://github.com/ulyanovskk/weblab2/blob/master/src/main/java/kotlin_meme_jsp/KJSP.kt)~~
### Notes
- No reload needs new architecture, new controller, new everything. No JSP table making? I guess... No JSP points making? I think so...
- Получение только новых данных в респонсе и их отображение (получается очень похоже на работу с php в первой лабе). В таком случае, вся прелесть JSP теряется, например, работа с bean'ами и taglib prefix "c".

  Решения: 
    1. Новая архитектура и сервлеты 
    2. ~~Autorefresh~~ is just reload :|
