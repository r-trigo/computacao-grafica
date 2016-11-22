package exercise91001;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Vector3f;

public class KeyController extends Behavior{
       //TransformGroup vai começar como Transform3D
       private TransformGroup tg = null;
       private Transform3D t3d = null;
       private WakeupCondition keyCriterion = null;
       
       //para as colisoes
       private Node collider;
       private boolean isColliding;
       private int lastKeyPress;
       //private Bounds bounds;
       
       public KeyController (TransformGroup tg, Node collider) {
              this.tg = tg;
              this.t3d = new Transform3D();
              this.collider = collider;
       }
       
       @Override
       public void initialize() {
              //wake-up condition fica neste método
              //clicar na tecla OU libertar a tecla -> 2 eventos para a mesma condição
              WakeupCriterion[] keyEvents = new WakeupCriterion[4]; //passa a 3 para as colisoes
              keyEvents[0] = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
              keyEvents[1] = new WakeupOnAWTEvent(KeyEvent.KEY_RELEASED); //não vamos usar, mas é só colocar o else
              keyEvents[2] = new WakeupOnCollisionEntry(collider); //o robo
              keyEvents[3] = new WakeupOnCollisionExit(collider); //para debug
              
              //wakeupcondition é a junção destas duas com o operador or
              keyCriterion = new WakeupOr(keyEvents);
              wakeupOn(keyCriterion);
       }

       @Override
       public void processStimulus(Enumeration criteria) {
              //nota: enumeration é param que dá acesso aos eventos
              //programação típica para processar os eventos
              WakeupCriterion wakeup;
              AWTEvent[] events;
              
              while (criteria.hasMoreElements()) {
                     //porque é enumeration, pode ser de qualquer tipo -> cast
                     wakeup = (WakeupCriterion) criteria.nextElement();
                     //events = ((WakeupOnAWTEvent) wakeup).getAWTEvent();
                     
                     if (wakeup instanceof WakeupOnCollisionEntry) {
                            //debug
                            //System.out.println("Entry com a tecla -> "+ lastKeyPress);
                            
                            // executa o movimento oposto ao ultimo executado
                            reverseMove();
                     } else if (wakeup instanceof WakeupOnCollisionExit) {
                            //debug
                            //System.out.println("Exit");
                     } else if (wakeup instanceof WakeupOnAWTEvent) {
                            //cast é necessário, porque método não existe
                            events = ((WakeupOnAWTEvent) wakeup).getAWTEvent();
                            
                            //processa os eventos
                            //podem ser de diversos tipos, dentro do AWT
                            //em função do tipo, podemos escolher as acções
                            for (int i = 0; i < events.length; ++i) {
                                   if(events[i].getID() == KeyEvent.KEY_PRESSED) {
                                          keyPressed((KeyEvent) events[i]);
                                   } //fim if
                            } //fim for
                     } //fim if
              } //fim while
              
              //sempre que o método é executado, volta a activar a condição
              wakeupOn(keyCriterion);
       }
       
       //o método que vai processar o evento
       private void keyPressed(KeyEvent event) {
              //a tecla pressionada
              int keyCode = event.getKeyCode();
              
              //guarda a ultima tecla processada
              lastKeyPress = keyCode;
              
              //processamento do evento
              switch (keyCode) {
              case KeyEvent.VK_LEFT:
                     doRotateY(Math.toRadians(2.0)); //faz a rotação em torno do Y
                     break;
              case KeyEvent.VK_RIGHT:
                     doRotateY(Math.toRadians(-2.0)); //rotação em sentido contrário
                     break;
              case KeyEvent.VK_UP:
                     doMove(new Vector3f(0.2f, 0, 0)); //para ficar genérica passa-se um vector3f
                     break;
              case KeyEvent.VK_DOWN:
                     doMove(new Vector3f(-0.2f, 0, 0)); //a mesma translação, mas negativa
                     break;
              } //fim switch
       } //fim keyPressed
       
       //executa o movimento oposto ao ultimo realizado para evitar colisao
       private void reverseMove() {
              switch (lastKeyPress) {
              case KeyEvent.VK_LEFT:
                       doRotateY(Math.toRadians(-2.0)); //faz a rotação em torno do Y
                       break;                     
              case KeyEvent.VK_RIGHT:
                       doRotateY(Math.toRadians(2.0)); //faz a rotação em torno do Y
                       break;
              case KeyEvent.VK_UP:
                       doMove(new Vector3f(-0.2f, 0.0f, 0f));
                       break;
              case KeyEvent.VK_DOWN:
                       doMove(new Vector3f(0.2f, 0.0f, 0));
                       break;
              } //fim case
       } //fim reverseMove
       
       //roda em torno do eixo Y
       private void doRotateY(double radians) {
              //a transformação é aplicada à transformação existente
              //ir ao tg move e obter a transformação geométrica do momento
              tg.getTransform(t3d); //fica guardada no t3d
              
              //para juntar, multiplica-se uma transformação pela outra
              Transform3D toRot = new Transform3D();
              toRot.rotY(radians);
              t3d.mul(toRot);
              
              //falta colocar a transformação no tg (actualizar o nó)
              tg.setTransform(t3d);
       }
       
       //move o robo no plano x
       private void doMove(Vector3f vector) {
              tg.getTransform(t3d);
              
              //para juntar, multiplica-se uma transformação pela outra
              Transform3D doMove = new Transform3D();
              doMove.setTranslation(vector);
              t3d.mul(doMove);
              
              //falta colocar a transformação no tg (actualizar o nó)
              tg.setTransform(t3d);
       }
}