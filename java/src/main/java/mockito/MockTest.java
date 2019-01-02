package mockito;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.atMost;
import static org.mockito.internal.verification.VerificationModeFactory.atLeast;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.verification.AtMost;

import com.google.common.collect.Range;

public class MockTest {

  @Test
  public void example() {
    Person p = mock(Person.class);
    assertTrue(p != null);

    when(p.getName()).thenReturn("winding");
    when(p.getAge()).thenReturn(20);
    assertTrue("winding".equals(p.getName()));
    assertTrue(20 == p.getAge());
  }

  @Mock
  Person p;

  @Test
  public void example1() {
    MockitoAnnotations.initMocks(this);
    assertTrue(p != null);
  }

  @Mock
  private MockIns mockIns;

  @Test
  public void example2() {
    MockitoAnnotations.initMocks(this);

    when(mockIns.getList(anyString(), anyInt()))
      .thenReturn(
        new ArrayList<String>() {
          {
            this.add("winding");
            this.add("road");
          }
        }
      );

    // 특정값 넣어야 할 때 eq
    // when(mockIns.getList(eq("JDM"), anyInt()))
    mockIns.getList("", 0).forEach((str) -> System.out.println(str));
  }

  @Test(expected = IllegalArgumentException.class)
  public void example3() {
    Person p = mock(Person.class);
    doThrow(new IllegalArgumentException()).when(p).setName(eq("winding"));
    String name = "winding";
    p.setName(name);
  }

  // doNothing
  @Test
  public void example4() {
    Person p = mock(Person.class);
    doNothing().when(p).setAge(anyInt());
    p.setAge(20);
    verify(p).setAge(anyInt());
  }

  /**
   * verifiy()는 해당 구문이 호출 되었는지를 체크합니다.
   * 단순한 호출뿐만 아니라 횟수나 타임아웃 시간까지 지정해서 체크해 볼 수 있습니다.
   */

  @Test
  public void example5() {
    Person p = mock(Person.class);
    String name = "JDM";

    p.setName(name); // 1번 호출

    // n번 호출했는지 체크
    verify(p, times(1)).setName(any(String.class)); // success

    // 호출 안했는지 체크
    verify(p, never()).getName(); // success
    verify(p, never()).setName(eq("winding")); // success
    verify(p, never()).setName(eq("road")); // fail

    // 최소한 1번 이상 호출했는지 체크
    verify(p, atLeastOnce()).setName(any(String.class)); // success

    // 2번 이하 호출 했는지 체크
    verify(p, atMost(2)).setName(any(String.class)); // success

    // 2번 이상 호출 했는지 체크
    verify(p, atLeast(2)).setName(any(String.class)); // fail

    // 지정된 시간(millis)안으로 메소드를 호출 했는지 체크
    verify(p, timeout(100)).setName(any(String.class)); // success

    // 지정된 시간(millis)안으로 1번 이상 메소드를 호출 했는지 체크
    verify(p, timeout(100).atLeast(1)).setName(any(String.class)); // success
  }

  @Mock
  AuthDao dao;

  @InjectMocks
  AuthService service;

  @Test
  public void example6() {
    MockitoAnnotations.initMocks(this);

    System.out.println("example6 test 1");
    when(dao.isLogin(eq("winding"))).thenReturn(true);
    assertTrue(service.isLogin("winding") == true);
    assertTrue(service.isLogin("road") == false);

    System.out.println("example6 test 2");
    when(dao.isLogin(eq("windingroad"))).thenReturn(false);
    assertTrue(service.isLogin("windingroad") == false);
  }

  @InjectMocks
  AuthService authService = new AuthService();

  @Mock
  AuthDao authDao;

  @Spy
  AuthDao authDaoSpy;

  @Test
  public void spyTest() {
    MockitoAnnotations.initMocks(this);

    /**
     * spy
     * stub method 를 만들지 않는다면, 실제 메소드 호출
     */
    assertTrue(authDaoSpy.isLogin("windingroad") == true);

    // mock
    assertTrue(authDao.isLogin("windingroad") == false);
  }
}
