package easings;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.Interpolator;

public class PanelTest extends JComponent {

    public void setEasing(Easings easing) {
        animator.setInterpolator(new Interpolator() {
            @Override
            public float interpolate(float f) {
                return easing.easing(f);
            }
        });
    }

    private Animator animator;
    private float animate;

    public PanelTest() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                animate = fraction;
                repaint();
            }
        };
        animator = new Animator(2000, target);
        animator.setResolution(0);
    }

    public void start() {
        if (!animator.isRunning()) {
            animator.start();
        }
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int size = 50;
        double width = getWidth() - size;
        g2.setColor(getBackground());
        g2.fillOval((int) (animate * width), 0, size, size);
        super.paint(grphcs);
    }
}
