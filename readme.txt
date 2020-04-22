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
