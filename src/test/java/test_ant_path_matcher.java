import org.jphil.utils.AntPathMatcher;
import org.jphil.utils.PathMatcher;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class test_ant_path_matcher {

    private final PathMatcher pathMatcher = new AntPathMatcher();


    @Test
    public void test_can_match() {
        assertTrue(pathMatcher.match("/**", "/"));
        assertTrue(pathMatcher.match("/**", "/dsfasdgfsdgsdfg/sdfagasfdgfd"));
        assertTrue(pathMatcher.match("/*", "/"));
        assertTrue(pathMatcher.match("/profile/{userId}", "/profile/1"));
        assertTrue(pathMatcher.match("/ho?me", "/hoLme"));
    }


}
