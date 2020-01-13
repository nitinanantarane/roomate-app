# roomate-app
#Perform a quick update on your instance:
sudo yum update -y

#Run below commands to install Java 11 on Amazon Linux:
sudo amazon-linux-extras install java-openjdk11

#Run below commands to install Java 8 on Amazon Linux:
sudo yum install java-1.8.0-openjdk
 
#Install git in your EC2 instance
sudo yum install git -y
 
#Check git version
git version

# Maven
sudo wget https://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
sudo yum install -y apache-maven

#set java_home
export JAVA_HOME="/usr/lib/jvm/java-openjdk"
export PATH=$PATH:$JAVA_HOME/bin
