import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
public class TestTournamentSort {

    private final Comparator<Employee> nameComp = new NameComparator();
    private final Comparator<Employee> salaryComp = new SalaryComparator();
    private final Employee elfa = new Employee("elfa", 10);
    private final Employee alfa = new Employee("alfa", 40);
    private final Employee dlfa = new Employee("dlfa", 20);
    private final Employee zlfa = new Employee("zlfa", 30);
    private final Employee[] em = new Employee[]{elfa, alfa, dlfa,zlfa};
    private TournamentSort<Employee> sorting = new TournamentSort<>(nameComp, em);

    private final Employee[] answ1 = new Employee[]{alfa, dlfa, elfa,zlfa};
    private final Employee[] answ2 = new Employee[]{elfa, dlfa, zlfa,alfa};
    @Test
    public void testSort(){
        assertEquals(sorting.sort(em, nameComp), answ1);
        assertEquals(sorting.sort(em, salaryComp), answ2);
    }

}
