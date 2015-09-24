public class mainFrame{
	
	ArrayList<Integer> ports = new ArrayList<Integer>();
	
	JButton button = new JButton("Start scan!");
	JTextField text1 = new JTextField("Enter first port");
	JTextField text2 = new JTextField("Enter last port");
	JTextArea report = new JTextArea(40,30);
	
	public Socket mySocket = null;
	
	
	public mainFrame(){
		
		JFrame frame = new JFrame();
		frame.setSize(400, 600);
	    frame.setResizable(false);
	    frame.setVisible(true);
	   
	    frame.addWindowListener(new WindowAdapter(){
	    	
	    	public void windowClosing(WindowEvent event){
	    	
	    		if (mySocket != null){
	    		
	    		try {
					mySocket.close();
				} catch (IOException e) {
					
					e.printStackTrace();
					System.exit(1);
				}
	    		}
	    		System.exit(0);
	    	}
	    	
	    });
	    
	    
	   
	    JPanel northPanel = new JPanel();
	    northPanel.add(text1);
	    northPanel.add(text2);
	    frame.add(northPanel, BorderLayout.NORTH);
	
	    JPanel centerPanel = new JPanel();
	    centerPanel.add(report);
	    frame.add(centerPanel, BorderLayout.CENTER);
	    
	    JPanel southPanel = new JPanel();
	    southPanel.add(button);
	    frame.add(southPanel, BorderLayout.SOUTH);
		
		


	    
	    text1.setEditable(true);
	    text2.setEditable(true);
	    report.setEditable(false);
	    
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	        
            	portScan();
            	showResults();
            	
				} 
                    
            
        });
	    	    	    
	}
	
	
	
	
	public void portScan(){
			int startPort = Integer.parseInt(text1.getText());
			int endPort = Integer.parseInt(text2.getText());
	
			for (int i = startPort; i < endPort + 1; i++){
				
				try{
					mySocket = new Socket("localhost", i);
					ports.add(i);
					mySocket.close();
				}
				
				catch (IOException ioEX){ System.out.println("Ingen port öppen på " + i);}
				
			}
		
		
	}
	
	
	
	
	public void showResults(){
			
	report.setText("Serverportar på Local Host:\n");

	for (int i = 0; i < ports.size(); i++) {
		
		Integer tempInt = ports.get(i);
		String temp = Integer.toString(tempInt);
		report.append(temp + "\n");
	}
	
	
	
						
		
		
	}
	
	
	
}
