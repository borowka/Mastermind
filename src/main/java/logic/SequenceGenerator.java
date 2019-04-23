package logic;

import model.CircleColor;

import java.util.List;

/**
 * Service to deliver the way of generate sequence
 *
 * @author Weronika
 */
public interface SequenceGenerator {
    List<CircleColor> getSequence(int size);
}
