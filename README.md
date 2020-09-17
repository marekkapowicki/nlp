## Optical Character Recognition
The Ocr process is being processed by [Apache Tika](https://tika.apache.org/1.24/index.html) that uses [Google Tesseract](https://opensource.google/projects/tesseract) 
under the hood. The ocr uses the [OkHttpClient](https://square.github.io/okhttp) to create the http call to Apache Tika Server 

### Start the server
To start the Apache Tika server just type a command 
``` 
docker-compose up
```
We can check if the servers are running through the URL [tika on localhost](http://localhost:9998/) as seen in the following image ![alt text](tika_main.png).
### Languages in Tesseract OCR
In the standard installation, the languages available in Tesseract are English (default), French, German, Italian, and Spanish. To add new ones, we need to access the container terminal through Docker and execute the following commands to install, for example, the Polish language. The correct choice of the text language allows greater precision in character recognition.
```
docker exec -it tika-server-ocr /bin/bash
apt-get update
apt-get install tesseract-ocr-por
```
###More reading
To read more about Tika ocr check this [blog post](https://medium.com/@masreis/text-extraction-and-ocr-with-apache-tika-302464895e5f)

###OCR in action
to see how the ocr process works in practice just send the sample file using curl
```
curl --request POST \
  --url http://localhost:8080/ocr \
  --header 'content-type: multipart/form-data; boundary=---011000010111000001101001' \
  --data-binary @file
```
