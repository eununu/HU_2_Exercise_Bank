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
	
	public void save(int money) //�Ա�
	{
		amount+= money;
	}
	
	public boolean draw(int money) //���
	{
		if(grade!=1 && amount< money) return false;
		amount-= money;
		return true;
	}
	
	public void print() //���
	{
		System.out.printf("%-5d%-5s%-20d%-5d\n",no,owner,amount,grade);
	}
	
	public int getAmount() //�ܾ�
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
	
	public void start() //�����۾� ����
	{
		int work;
		while (true)
		{
			System.out.println("--------------------------------------------------------------------");
			System.out.print("1)���� 2)�Ա� 3)��� 4)�۱� 5)�������� 6)���峻����� 7)���� == > ");
			work= s.nextInt();
			System.out.println("--------------------------------------------------------------------");
			
			if(work==7)
			{
				System.out.println("����Ǿ����ϴ�.");
				break;
			}
			
			else if(work==1)
			{
				boolean result= addAccount();
				
				if(result== true)
				{
					System.out.println("���°� �����Ǿ����ϴ�.");
					count++;
				}
				else
					System.out.println("���̻� ���¸� ���� �� �� �����ϴ�.");
			}
			
			else if(work==2)saveMoney();
			else if(work==3)drawMoney();
			else if(work==4)transferMoney();
			else if(work==5)putInterest();
			else printAccount();
		}
	}
	
	public boolean addAccount() //���� ����
	{
		if(count> 10) return false;
		System.out.println("���� ������ �����մϴ�.");
		
		System.out.print("������ �̸� : ");
		owner= s.next();
		System.out.print("���� ���Ա� : ");
		money= s.nextInt();
		System.out.print("�ſ��� : ");
		grade= s.nextInt();
		
		accounts[count-1]= new BankAccount(count,owner,money,grade);
		return true;
	}
	
	public void saveMoney() //Ư�� ���忡 �Ա�
	{
		System.out.print("�Ա��Ͻ� ���¹�ȣ�� �Է��� �ֽʽÿ�. ");
		no= s.nextInt();
		viewAccount(no);
		System.out.print("�� ������ ���°� �½��ϱ�? �Ա��Ͻðڽ��ϱ�?(Y/N)");
		YN= s.next();
		
		if(YN.equals("Y"))
		{
			System.out.print("�Ա��Ͻ� �ݾ��� �Է��� �ֽʽÿ�. " );
			money= s.nextInt();
			accounts[no-1].save(money);
			System.out.println("�ԱݵǾ����ϴ�.");
		}
	}
	
	public void drawMoney() //Ư�� ���忡�� ���
	{
		System.out.print("����Ͻ� ���¹�ȣ�� �Է��� �ֽʽÿ�. ");
		no= s.nextInt();
		viewAccount(no);
		System.out.print("�� ������ ���°� �½��ϱ�? ����Ͻðڽ��ϱ�?(Y/N)");
		YN= s.next();
		
		if(YN.equals("Y"))
		{
			System.out.print("����Ͻ� �ݾ��� �Է��� �ֽʽÿ�. " );
			money= s.nextInt();
		
			if(accounts[no-1].draw(money))
				System.out.println("��ݵǾ����ϴ�.");
			
			else System.out.println("�ܾ��� �����մϴ�.");
		}
	}
	
	public void printAccount() //���峻�� ���
	{
		System.out.printf("-------------------------\n");
		System.out.printf("%-5S%-5S%-20S%-5S\n","�����ȣ","������","�ܾ�","�ſ���");
		for(int i=0;i<count-1;i++)
		{
			accounts[i].print();
		}
		System.out.printf("-------------------------\n");
	}
	
	public void viewAccount(int num) //�������� Ȯ��
	{
		System.out.printf("-------------------------\n");
		System.out.printf("%-5S%-5S%-20S%-5S\n","�����ȣ","������","�ܾ�","�ſ���");
		accounts[num-1].print();
		System.out.printf("-------------------------\n");
	}
	
	public void transferMoney() //�۱�
	{
		System.out.print("���� ���� ���¹�ȣ�� �ݾ��� �Է��� �ֽʽÿ�. ");
		no1= s.nextInt();
		money= s.nextInt();
		viewAccount(no1);
		System.out.println("�� ������ ���°� �½��ϱ�? �۱��Ͻðڽ��ϱ�? (Y/N)");
		YN= s.next();
		if(YN.equals("Y"))
		{
			if(accounts[no1-1].draw(money))
			{
				System.out.print("���� ���� ���¹�ȣ�� �Է��� �ֽʽÿ�. ");
				no2= s.nextInt();
				viewAccount(no2);
				System.out.println("�� ������ ���°� �½��ϱ�? �۱��Ͻðڽ��ϱ�? (Y/N)");
				YN= s.next();
				if(YN.equals("Y")) 
				{
					accounts[no2-1].save(money);
					System.out.println("�۱ݵǾ����ϴ�.");
				}
				else accounts[no1-1].save(money);
			}
			else System.out.println("�ܾ��� �����մϴ�.");
		}
	}
	
	public void putInterest() //��������
	{
		for(int i=0;i<count-1;i++)
		{
			if(accounts[i].grade==1 && accounts[i].amount<0) continue;
			accounts[i].amount= (int) (rate[accounts[i].grade-1]*accounts[i].amount);
			accounts[i].amount= (accounts[i].amount/10) * 10;
		}
		System.out.println("���� ������ �Ϸ�Ǿ����ϴ�.");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountManager m = new AccountManager();
		m.start();
	}
}
