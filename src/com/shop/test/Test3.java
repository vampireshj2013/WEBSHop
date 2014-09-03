/*#include<stdio.h>
#include<stdlib.h>
#include<math.h>
int isprime(long m){
	int n,i;
	n=sqrt(m);
	for(i=2;i<=n;i++){
		if(m%i==0){
			break;
		}
	}
	if(i>n){
		return 1;
	}
	else{
		return 0;
	}
}
long reverseNum(long n){
	long m;
	for(m=0;n>0;){
		m=m*10+n%10;
		n=n/10;
	}
	return m;
}
void main(){
	char filename[]="D:\\result3.txt";
	FILE *fp;
	int i=10;
	fp=fopen(filename,"w+");
	if(fp==NULL){
		printf("file write error!\n");
		exit(0);
	}
	for(;i<1000;i++)
	{
		if(isprime(i)&&isprime(reverseNum(i)){
			printf("%d\t",i);
			fprintf(fp,"%d\t",i);
		}
	}
	fclose(fp);
}
*/