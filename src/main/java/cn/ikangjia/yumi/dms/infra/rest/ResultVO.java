package cn.ikangjia.yumi.dms.infra.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {
    private Integer code;
    private T data;
    private String msg;

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(0, data, "操作成功");
    }

    public static <T> ResultVO<T> error(String msg) {
        return new ResultVO<>(1, null, msg);
    }

}
