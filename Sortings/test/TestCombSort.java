import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
public class TestCombSort {

    private final Comparator<Employee> nameComp = new NameComparator();
    private final Comparator<Employee> salaryComp = new SalaryComparator();
    private final CombSort<Employee> sorting = new CombSort<>();
    private final Employee elfa = new Employee("elfa", 10);
    private final Employee alfa = new Employee("alfa", 40);
    private final Employee dlfa = new Employee("dlfa", 20);
    private final Employee zlfa = new Employee("zlfa", 30);
    private final List<Employee> em = new ArrayList<>(){{add(elfa); add(alfa); add(dlfa); add(zlfa);}};
    private final List<Employee> asnw1 = new ArrayList<>(){{add(alfa);add(dlfa); add(elfa); add(zlfa);}};
    private final List<Employee> answ2 = new ArrayList<>(){{add(zlfa);add(elfa); add(dlfa); add(alfa);}};
    private final List<Employee> answ3 = new ArrayList<>(){{add(elfa);add(dlfa); add(zlfa); add(alfa);}};
    private final List<Employee> answ4 = new ArrayList<>(){{add(alfa);add(zlfa); add(dlfa); add(elfa);}};
    @Test
    public void testSortByIncrease(){
        assertEquals(sorting.sortByIncrease(em, nameComp), asnw1);
        assertEquals(sorting.sortByIncrease(em, salaryComp), answ3);
    }
    @Test
    public void testSortByDecrease(){
        assertEquals(sorting.sortByDecrease(em, nameComp), answ2);
        assertEquals(sorting.sortByDecrease(em, salaryComp), answ4);
    }
}
