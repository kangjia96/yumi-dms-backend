package cn.ikangjia.yumi.dms.infra.config;

import cn.ikangjia.yumi.dms.domain.entity.UserDO;
import cn.ikangjia.yumi.dms.service.impl.UserServiceImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.security.auth.login.LoginException;
import java.util.Objects;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/8 20:54
 */
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        // 如果不是映射到方法直接通过,可以访问资源.
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("Token");
        if (!StringUtils.hasText(token)) {
            throw new RuntimeException("请先登录~");
        }
        String userId = JWT.decode(token).getAudience().getFirst();
        WebApplicationContext context = RequestContextUtils.findWebApplicationContext(request);
        UserServiceImpl userService = Objects.requireNonNull(context).getBean(UserServiceImpl.class);
        UserDO user = userService.getUser(Long.valueOf(userId));
        if (user == null) {
            throw new RuntimeException("用户不存在～");
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new LoginException("Token 认证失败～");
        }
        return true;
    }

    @Override
    public void postHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
