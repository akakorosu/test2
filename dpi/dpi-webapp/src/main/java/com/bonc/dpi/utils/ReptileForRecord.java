package com.bonc.dpi.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/*
 * 查询域名是否备案 
 */
public class ReptileForRecord  implements Runnable{

	public int total;
	public List<String> shouldSave; 
	public List<String> shouldDelete;
	public List<String> list =ReadWriteTxt.readText("C:\\Users\\Administrator\\Desktop\\无标题域名\\4.txt",null);
	
	public ReptileForRecord() {
		total = 0;
		shouldSave = new LinkedList<String>();
		shouldDelete  = new LinkedList<String>();
	}
	
	@Override
	public void run() {
		boolean result = false;
		while (total < list.size()) {
			synchronized (this) {
				if(total < list.size()) {
					try {
						result = ConnectNetwork.isValid(list.get(total));
						Thread.sleep(new Random().nextInt(20));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(result){
						shouldSave.add(list.get(total));
					}else{
						shouldDelete.add(list.get(total));
					}
					total++;
				}
			}
		}
		if(total==list.size()){
			System.out.println("总计"+list.size()+"条数据分析完毕");
			ReadWriteTxt.writeToFile2("shouldDelete4.txt", "C:\\Users\\Administrator\\Desktop\\", shouldDelete);
			ReadWriteTxt.writeToFile2("shouldSave4.txt", "C:\\Users\\Administrator\\Desktop\\", shouldSave);
		}
	}
	public static void main(String[] args) {
		ReptileForRecord st = new ReptileForRecord();
		new Thread(st, "线程1").start();
		new Thread(st, "线程2").start();
		new Thread(st, "线程3").start();
		new Thread(st, "线程4").start();
		new Thread(st, "线程5").start();
		new Thread(st, "线程6").start();
		new Thread(st, "线程7").start();
		new Thread(st, "线程8").start();
	}

}
