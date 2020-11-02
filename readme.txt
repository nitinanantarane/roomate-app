--- install Oracle JDK 1.8
$sudo wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u141-b15/336fa29ff2bb4ef291e347e091f7f4a7/jdk-8u141-linux-x64.rpm
$sudo yum install -y jdk-8u141-linux-x64.rpm

---- install OpenJDK
$ vi install.sh
sudo yum install -y java-1.8.0-openjdk-devel
sudo yum remove java-1.7.0-openjdk
sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
sudo yum install -y apache-maven
mvn --version

sudo yum install -y git 

$ chmod 755 install.sh
$ sudo ./install.sh


---- python
sudo pip install gunicorn
sudo pip install -r requirements.txt

---- docker
sudo yum update -y
sudo yum install docker -y
sudo docker images
sudo usermod -a -G docker ec2-user
sudo service docker start
sudo service docker status
sudo docker build -f Dockerfile -t docker-spring-boot .
sudo docker run -p 8085:8085 docker-spring-boot

aws ecr create-repository --repository-name spring-boot-repo --region us-east-1
aws ecr get-login --no-include-email --region us-east-1
docker tag ae15611663b9 415024401597.dkr.ecr.us-east-1.amazonaws.com/hello-sb-repository:latest
docker push 415024401597.dkr.ecr.us-east-1.amazonaws.com/hello-sb-repository:latest

----- node js
curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.34.0/install.sh | bash
. ~/.nvm/nvm.sh
nvm install node
node -e "console.log('Running Node.js ' + process.version)"

----- lambda
https://aws.amazon.com/blogs/opensource/java-apis-aws-lambda/

mvn archetype:generate -DgroupId=my.service -DartifactId=jersey-sample -Dversion=1.0-SNAPSHOT \
      -DarchetypeGroupId=com.amazonaws.serverless.archetypes \
	  -DarchetypeArtifactId=aws-serverless-jersey-archetype \
	  -DarchetypeVersion=1.0.1 -Dinteractive=false
    
Local EC2
npm install -g aws-sam-local

$ curl -s http://127.0.0.1:3000/ping | python -m json.tool

{
    "pong": "Hello, World!"
}

Deploy to AWS
aws cloudformation package --template-file sam.yaml --output-template-file output-sam.yaml --s3-bucket <YOUR S3 BUCKET NAME>
Uploading to xxxxxxxxxxxxxxxxxxxxxxxxxx  6464692 / 6464692.0  (100.00%)
Successfully packaged artifacts and wrote output template to file output-sam.yaml.
Execute the following command to deploy the packaged template
aws cloudformation deploy --template-file /your/path/output-sam.yaml --stack-name <YOUR STACK NAME>

aws cloudformation deploy --template-file output-sam.yaml --stack-name ServerlessJerseyApi --capabilities CAPABILITY_IAM

aws cloudformation describe-stacks --stack-name ServerlessJerseyApi --query 'Stacks[0].Outputs[*].{Service:OutputKey,Endpoint:OutputValue}'
[
    {
		"Service": "JerseySampleApi",
		"Endpoint": "https://xxxxxxx.execute-api.us-west-2.amazonaws.com/Prod/ping"
    }
]

curl -s https://xxxxxxx.execute-api.us-west-2.amazonaws.com/Prod/ping | python -m json.tool

{
    "pong": "Hello, World!"
}


##CUCUMBER
clean -Denv=qa -Dtest=**/*Runner test install
