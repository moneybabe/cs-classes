# Researching Commands: `find`

## Using `-name` flag
**First Example:**
```
neo@Neos-MacBook-Air docsearch-main % find technical -name "chapter*"
```
```
technical/911report/chapter-13.4.txt
technical/911report/chapter-13.5.txt
technical/911report/chapter-13.1.txt
technical/911report/chapter-13.2.txt
technical/911report/chapter-13.3.txt
technical/911report/chapter-3.txt
technical/911report/chapter-2.txt
technical/911report/chapter-1.txt
technical/911report/chapter-5.txt
technical/911report/chapter-6.txt
technical/911report/chapter-7.txt
technical/911report/chapter-9.txt
technical/911report/chapter-8.txt
technical/911report/chapter-12.txt
technical/911report/chapter-10.txt
technical/911report/chapter-11.txt
```
- It is finding all directories and files with path starting with the keyword "chapter". It is useful when you want to find some specific files in a directory but you only know part of the path name. For example, if you want to find all the pictures with ".png" extension, you can do `$find . -name "*.png"`.

**Second Example:**
```
neo@Neos-MacBook-Air docsearch-main % find technical -name "*2023*"
```
```
technical/plos/journal.pbio.0020232.txt
technical/plos/pmed.0020238.txt
technical/plos/pmed.0020239.txt
technical/plos/pmed.0020231.txt
technical/plos/pmed.0020232.txt
technical/plos/pmed.0020237.txt
technical/plos/pmed.0020236.txt
technical/plos/pmed.0020235.txt
```
- It is finding all directories and files with path containing "2023", which can be either in the beginning, end, or middle of the path pattern. It is useful because now instead of just finding files that starts or ends with a specific pattern, the pattern can appear anywhere within the path.

## Using `-size` flag
**First Example:**
```
neo@Neos-MacBook-Air docsearch-main % find technical -size +200k
```
```
technical/government/About_LSC/commission_report.txt
technical/government/Env_Prot_Agen/bill.txt
technical/government/Gen_Account_Office/GovernmentAuditingStandards_yb2002ed.txt
technical/government/Gen_Account_Office/Statements_Feb28-1997_volume.txt
technical/government/Gen_Account_Office/d01591sp.txt
technical/911report/chapter-13.4.txt
technical/911report/chapter-13.5.txt
technical/911report/chapter-3.txt
```
- It is finding all directories and files that have size more than 200 kilobytes. It is usefule when you are trying to figure out what files are taking up so much space in your storage. You can filter them out in this way.

**Second Example:**
```
neo@Neos-MacBook-Air docsearch-main % find technical -size -200c
```
```
technical/government/Alcohol_Problems
```
- It is finding all directories and files that have size lower than 200 bytes. It is useful when you completely forget the name of some files, but you know it's a decently small file, then you can narrow the scope by filtering out the bigger files.

## Using `-type` flag
**First Example:**
```
neo@Neos-MacBook-Air docsearch-main % find technical/government -type d
```
```
technical/government
technical/government/About_LSC
technical/government/Env_Prot_Agen
technical/government/Alcohol_Problems
technical/government/Gen_Account_Office
technical/government/Post_Rate_Comm
technical/government/Media
```
- It is finding only directories inside the government directory. It is useful when you only want to find directories, and don't care about files.

**Second Example:**
```
neo@Neos-MacBook-Air docsearch-main % find technical/government -type f
```
```
technical/government/.DS_Store
technical/government/About_LSC/LegalServCorp_v_VelazquezSyllabus.txt
technical/government/About_LSC/Progress_report.txt
technical/government/About_LSC/Strategic_report.txt
technical/government/About_LSC/Comments_on_semiannual.txt
technical/government/About_LSC/Special_report_to_congress.txt
...... [tons of output]
```
- It is finding only files inside the government directory. It is useful when you only want to find files, and don't want the unecessary directories path to overwhelm your terminal.

## Using `-not` flag
**First Example:**
```
neo@Neos-MacBook-Air docsearch-main % find technical -not -name "*.txt"
```
```
technical
technical/government
technical/government/.DS_Store
technical/government/About_LSC
technical/government/Env_Prot_Agen
technical/government/Alcohol_Problems
technical/government/Gen_Account_Office
technical/government/Post_Rate_Comm
technical/government/Media
technical/.DS_Store
technical/plos
technical/biomed
technical/911report
```
- It is finding all directories and files with path name that doesn't end with ".txt" extension. It is useful when you want to do negation of some conditions.

**Second Example:**
```
neo@Neos-MacBook-Air docsearch-main % find technical -size -1000c -not -name "pmed*"
```
```
technical
technical/government
technical/government/About_LSC
technical/government/Env_Prot_Agen
technical/government/Alcohol_Problems
technical/government/Post_Rate_Comm
technical/911report
```
- It is finding all directories and files that has size under 1000 bytes and the path name not starting with "pmed". It is useful when you want to do negation of some conditions. It also shows that multiple flags can be used together to make a chain of conditions. So if you want to perform an operation with multiple steps, you can chain it all in one command without separating into multiple commands.

## Sources
- I find all the command usage by simply asking Bing. I asked for the **most common** usage of using `find`. By directly asking Bing, I can efficiently learn the most commonly used commands because it already aggregated most of the internet information for me. This way, I wouldn't need to look into documentation and bunch of random variations that I would only use very occasionally.
![Image](images/sources/bing.png)