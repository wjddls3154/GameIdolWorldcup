
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IdolWorldCup {

    static JPanel panelNorth;   // 위쪽 패널
    static JPanel panelCenter;  // 가운데 패널
    static JLabel labelMessage;  // 타이틀 문구 표현해줄 레이블
    static JLabel labelVs; // vs 문구
    static JButton buttonLeft; // 버튼
    static JButton buttonRight;

    // 이미지 배열에 저장
    static String[] images = {
            "idol1.png", "idol2.png", "idol3.png","idol4.png",
            "idol5.png", "idol6.png","idol7.png", "idol8.png",
            "idol9.png","idol10.png", "idol11.png", "idol12.png",
            "idol13.png", "idol14.png", "idol15.png","idol16.png"
    };

    // 이미지 인덱스
    static int imageIndex = 2; // 처음에 2장이 표현이 되기때문에, [0,1,] 을 제외한 [2] 3번째 사진의 인덱스 가져옴

    // image 를 버튼 컨트롤에 추가하기 위한 메소드
    static ImageIcon changeImage(String filename) {
        ImageIcon icon = new ImageIcon("./" + filename);
        Image originImage = icon.getImage();
        Image changeImage = originImage.getScaledInstance(200,200,Image.SCALE_SMOOTH);
        ImageIcon icon_new = new ImageIcon(changeImage);
        return icon_new;
    }



    static class MyFrame extends JFrame implements ActionListener {

        // 생성자
        public MyFrame(String title) {
            super(title);

            // UI 초기화
            this.setSize(450,250);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 위쪽 패널 설정
            panelNorth = new JPanel();
            labelMessage = new JLabel("Find your ideal type - 16! ");
            labelMessage.setFont(new Font("Arial",Font.BOLD,20));
            panelNorth.add(labelMessage);
            this.add("North",panelNorth);

            // 가운데 패널 설정
            panelCenter = new JPanel();
            labelVs = new JLabel("vs");
            labelVs.setFont(new Font("Arial",Font.BOLD,20));

            // 왼쪽 버튼 설정
            buttonLeft = new JButton("LeftButton");
            buttonLeft.setIcon(changeImage("idol1.png"));
            buttonLeft.setPreferredSize(new Dimension(200,200)); // 이미지는 setSize 보다 setPreferredSize 가 더 좋다.

            // 오른쪽 버튼 설정
            buttonRight = new JButton("RightButton");
            buttonRight.setIcon(changeImage("idol2.png"));
            buttonRight.setPreferredSize(new Dimension(200,200));

            // 버튼 클릭 이벤트
            buttonLeft.addActionListener(this); // 이 이벤트는 내가 받는다. : this
            buttonRight.addActionListener(this);

            // 가운데 패널에 레이블, 버튼 추가
            panelCenter.add(buttonLeft);
            panelCenter.add(labelVs);
            panelCenter.add(buttonRight);

            this.add("Center",panelCenter); // 설정한 가운데 패널을 this 에 추가

            this.pack(); // 빈 공간을 없애주고 감싸준다.

        }

        // 버튼 클릭 이벤트를 받기위한 것
        @Override
        public void actionPerformed(ActionEvent e) {
            if (imageIndex == 16) { // 마지막 사진
                labelMessage.setText("Found Your Ideal Type !"); // 문구 띄워준다

                // 마지막에 선택한 하나남은 이미지만 보여준다.
                if(e.getActionCommand().equals("LeftButton"))  { // 마지막 선택한게 왼쪽버튼 이미지면 오른쪽과 vs레이블 감춘다.
                        buttonRight.hide();
                        labelVs.hide();
                } else { // 오른쪽 이미지면 ~
                    buttonLeft.hide();
                    labelVs.hide();
                }

            } else {
                if (e.getActionCommand().equals("LeftButton")) { // LeftButton 눌렀을 때
                    buttonRight.setIcon(changeImage(images[imageIndex])); // 왼쪽 버튼 눌렀을 땐 오른쪽 이미지 바뀌도록 이미지 인덱스 가져와서 세팅
                    imageIndex++;
                } else { // RightButton 눌렀을 때
                    buttonLeft.setIcon(changeImage(images[imageIndex])); // 오른쪽 버튼 눌렀을 땐 왼쪽 이미지 바뀌도록 이미지 인덱스 가져와서 세팅
                    imageIndex++;
                }
            }
        }
    }

    public static void main(String[] args) {
        new MyFrame("My Idol World Cup");

    }

}
