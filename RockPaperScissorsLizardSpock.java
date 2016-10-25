import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
/**
 * Created by mfcobo on 10/25/16.
 */
public class RockPaperScissorsLizardSpock extends Frame {
    private Label yourChoice;
    private CheckboxGroup choices;
    private TextArea results;
    private Button gameOn;
    private Label playerScore;
    private Label computerScore;
    private TextField playerOne;
    private TextField computer;
    private Checkbox rock;
    private Checkbox paper;
    private Checkbox scissors;
    private Checkbox lizard;
    private Checkbox spock;
    private int command[][];
    private int result;
    private String choice[];
    private Dialog dialog;
    private Button dialogButton;
    private Label dialogLabel;
    private int userSelected;
    private int compSelected;
    private int userScore;
    private int compScore;

    public RockPaperScissorsLizardSpock(){
    	setLayout(new FlowLayout());

    	command = new int[][]{
    		{0,2,1,1,2},
    		{1,0,2,2,1},
    		{2,1,0,1,2},
    		{2,1,2,0,1},
    		{1,2,1,2,0},
    	};

    	choice = new String[]{"ROCK","PAPER","SCISSORS","LIZARD","SPOCK"};

    	yourChoice = new Label("Your choice:"); 
    	choices = new CheckboxGroup();
    	
    	rock  = new Checkbox("Rock",choices,true);
    	paper = new Checkbox("Paper",choices,false);
    	scissors = new Checkbox("Scissors",choices,false);
    	lizard = new Checkbox("Lizard",choices,false);
    	spock = new Checkbox("Spock",choices,false);

	results = new TextArea("Results:",5,25); 
	results.setEditable(false);
    	gameOn = new Button("RockPaperScissorsLizardSpock");
    	playerScore = new Label("Player's Score      ");
    	computerScore = new Label("Computer's Score");
    	playerOne = new TextField("",10);
    	playerOne.setEditable(false);
    	computer = new TextField("",10);
    	computer.setEditable(false);

    	userScore = 0;
    	compScore = 0;

    	dialog = new Dialog(this,"Game Over", Dialog.ModalityType.DOCUMENT_MODAL);
    	dialog.setLayout(new FlowLayout());
    	dialogButton = new Button("OK");
    	dialogButton.setSize(60,40);
    	dialogLabel = new Label();

	dialog.setSize(230,100);
    	dialog.add(dialogLabel);
    	dialog.add(dialogButton);
    	
    	//add to container
    	add(yourChoice);
    	add(rock);
    	add(paper);
    	add(scissors);
    	add(lizard);
    	add(spock);
    	add(results);//Results text area
    	add(gameOn);//
    	add(playerScore);
    	add(playerOne);
    	add(computerScore);
    	add(computer);

    	setTitle("RockPaperScissorsLizardSpock");
        setSize(300, 300);
        setVisible(true);

    	 // 1. provide new class:
        //btnSum.addActionListener(new MyActionListener());
        // 2. anonymous class:
        gameOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	checkBox();//check for the winner
            	results.setText("Results:\nPlayer chose " + choice[userSelected] +"\n" + "Computer chose "+ choice[compSelected] + "\n\n");
            	if(result == 0){
            		results.append("IT'S A TIE!");
            	}
            	else if(result == 1){
            		results.append("YOU WIN THIS ROUND!");
            		userScore++;
            	}
            	else{
            		results.append("YOU LOSE THIS ROUND!");
            		compScore++;
            	}
            	playerOne.setText(userScore + "");
            	computer.setText(compScore + "");
            	if(userScore == 5 || compScore == 5){
            		promptWinner();
            	}
            }
        });
        //reset game
        dialogButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		userScore = 0;
        		compScore = 0;
        		results.setText("Results:");
        		playerOne.setText("");
        		computer.setText("");
        		dialog.dispose();
        	}
        });	
        //close window
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

    	
        // close window
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    public void promptWinner(){
    	if(userScore == 5){
    		dialogLabel.setText("Player won.");
    	}
    	else{
    		dialogLabel.setText("Computer won.");
    	}
    	dialog.setVisible(true);
    }		

    public void checkBox(){
    	if(choices.getSelectedCheckbox() == rock){
    		userSelected = 0;
    	}
    	else if(choices.getSelectedCheckbox() == paper){
    		userSelected = 1;
    	}
    	else if(choices.getSelectedCheckbox() == scissors){
    		userSelected = 2;
    	}
    	else if(choices.getSelectedCheckbox() == lizard){
    		userSelected = 3;
    	}
    	else{
    		userSelected = 4;
    	}
    	compute();
    }

    public void randomInteger(int min, int max) {
    	Random rand = new Random();
    	compSelected = min + (int)(Math.random() * ((max - min) + 1));
	}

    public void compute(){
    	randomInteger(0,4);
    	result = command[userSelected][compSelected]; 
    }
    
    public static void main(String[] args){
        new RockPaperScissorsLizardSpock();
   	}
}
