import java.util.Scanner;


class BankAccount
{
	int no;
	String owner;
	int amount, grade;
	
	public BankAccount(int no,String owner,int money,int grade)
	{
		this.no= no;
		this.owner= owner;
		this.amount= money;
		this.grade= grade;
	}
	
	public void save(int money) //입금
	{
		amount+= money;
	}
	
	public boolean draw(int money) //출금
	{
		if(grade!=1 && amount< money) return false;
		amount-= money;
		return true;
	}
	
	public void print() //출력
	{
		System.out.printf("%-5d%-5s%-20d%-5d\n",no,owner,amount,grade);
	}
	
	public int getAmount() //잔액
	{
		return amount;
	}
}
public class AccountManager {

	int count= 1;
	BankAccount accounts[] = new BankAccount[10];
	double rate[]= {1.03,1.02,1.01};
	Scanner s= new Scanner(System.in);
	int no,no1,no2,money,grade;
	String owner,YN;
	
	public void start() //관리작업 진행
	{
		int work;
		while (true)
		{
			System.out.println("--------------------------------------------------------------------");
			System.out.print("1)개설 2)입금 3)출금 4)송금 5)이자지급 6)통장내역출력 7)종료 == > ");
			work= s.nextInt();
			System.out.println("--------------------------------------------------------------------");
			
			if(work==7)
			{
				System.out.println("종료되었습니다.");
				break;
			}
			
			else if(work==1)
			{
				boolean result= addAccount();
				
				if(result== true)
				{
					System.out.println("계좌가 개설되었습니다.");
					count++;
				}
				else
					System.out.println("더이상 계좌를 개설 할 수 없습니다.");
			}
			
			else if(work==2)saveMoney();
			else if(work==3)drawMoney();
			else if(work==4)transferMoney();
			else if(work==5)putInterest();
			else printAccount();
		}
	}
	
	public boolean addAccount() //통장 개설
	{
		if(count> 10) return false;
		System.out.println("계좌 개설이 가능합니다.");
		
		System.out.print("예금주 이름 : ");
		owner= s.next();
		System.out.print("최초 불입금 : ");
		money= s.nextInt();
		System.out.print("신용등급 : ");
		grade= s.nextInt();
		
		accounts[count-1]= new BankAccount(count,owner,money,grade);
		return true;
	}
	
	public void saveMoney() //특정 통장에 입금
	{
		System.out.print("입금하실 계좌번호를 입력해 주십시오. ");
		no= s.nextInt();
		viewAccount(no);
		System.out.print("위 정보의 계좌가 맞습니까? 입금하시겠습니까?(Y/N)");
		YN= s.next();
		
		if(YN.equals("Y"))
		{
			System.out.print("입금하실 금액을 입력해 주십시오. " );
			money= s.nextInt();
			accounts[no-1].save(money);
			System.out.println("입금되었습니다.");
		}
	}
	
	public void drawMoney() //특정 통장에서 출금
	{
		System.out.print("출금하실 계좌번호를 입력해 주십시오. ");
		no= s.nextInt();
		viewAccount(no);
		System.out.print("위 정보의 계좌가 맞습니까? 출금하시겠습니까?(Y/N)");
		YN= s.next();
		
		if(YN.equals("Y"))
		{
			System.out.print("출금하실 금액을 입력해 주십시오. " );
			money= s.nextInt();
		
			if(accounts[no-1].draw(money))
				System.out.println("출금되었습니다.");
			
			else System.out.println("잔액이 부족합니다.");
		}
	}
	
	public void printAccount() //통장내역 출력
	{
		System.out.printf("-------------------------\n");
		System.out.printf("%-5S%-5S%-20S%-5S\n","통장번호","예금주","잔액","신용등급");
		for(int i=0;i<count-1;i++)
		{
			accounts[i].print();
		}
		System.out.printf("-------------------------\n");
	}
	
	public void viewAccount(int num) //통장정보 확인
	{
		System.out.printf("-------------------------\n");
		System.out.printf("%-5S%-5S%-20S%-5S\n","통장번호","예금주","잔액","신용등급");
		accounts[num-1].print();
		System.out.printf("-------------------------\n");
	}
	
	public void transferMoney() //송금
	{
		System.out.print("돈을 보낼 계좌번호와 금액을 입력해 주십시오. ");
		no1= s.nextInt();
		money= s.nextInt();
		viewAccount(no1);
		System.out.println("위 정보의 계좌가 맞습니까? 송금하시겠습니까? (Y/N)");
		YN= s.next();
		if(YN.equals("Y"))
		{
			if(accounts[no1-1].draw(money))
			{
				System.out.print("돈을 받을 계좌번호를 입력해 주십시오. ");
				no2= s.nextInt();
				viewAccount(no2);
				System.out.println("위 정보의 계좌가 맞습니까? 송금하시겠습니까? (Y/N)");
				YN= s.next();
				if(YN.equals("Y")) 
				{
					accounts[no2-1].save(money);
					System.out.println("송금되었습니다.");
				}
				else accounts[no1-1].save(money);
			}
			else System.out.println("잔액이 부족합니다.");
		}
	}
	
	public void putInterest() //이자지급
	{
		for(int i=0;i<count-1;i++)
		{
			if(accounts[i].grade==1 && accounts[i].amount<0) continue;
			accounts[i].amount= (int) (rate[accounts[i].grade-1]*accounts[i].amount);
			accounts[i].amount= (accounts[i].amount/10) * 10;
		}
		System.out.println("이자 지급이 완료되었습니다.");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountManager m = new AccountManager();
		m.start();
	}
}
