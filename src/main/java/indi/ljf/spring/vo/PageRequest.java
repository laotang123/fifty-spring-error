package indi.ljf.spring.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author liujf14
 */
@Data
@ApiModel(value = "通用分页查询请求实体")
public class PageRequest <T> implements Serializable {
    @Valid
    @NotNull(message = "分页查询过滤条件不能为空")
    @ApiModelProperty(value = "分页查询过滤条件")
    private T filter;

    @ApiModelProperty(value = "分页查询页码")
    private Integer pageIndex;

    @ApiModelProperty(value = "分页查询数据条数")
    private Integer pageSize;
}
