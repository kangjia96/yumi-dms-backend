package cn.ikangjia.yumi.dms.infra.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2024/2/1 15:38
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {
    private Integer code;
    private String message;
    private T data;

   public static final int SUCCESS = 0;
   public static final int ERROR = 1;

   public static <T> ResultVO<T> success(){
       return new ResultVO<>(SUCCESS, "success", null);
   }

   public static <T> ResultVO<T> success(T data){
       return new ResultVO<>(SUCCESS, "success", data);
   }

   public static <T> ResultVO<T> error(String message){
       return new ResultVO<>(ERROR, message, null);
   }
}
