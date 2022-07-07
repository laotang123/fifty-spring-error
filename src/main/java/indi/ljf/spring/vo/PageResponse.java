package indi.ljf.spring.vo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author liujf14
 */
@Data
@ApiModel(value = "通用分页查询响应实体")
public class PageResponse<T> implements Serializable {
    @ApiModelProperty(value = "结果集")
    private List<T> list;

    @ApiModelProperty(value = "总数据条数")
    private Long total;

    @ApiModelProperty("总页数")
    private Integer pages;

    /**
     * 封装通用分页响应
     *
     * @param list 分页数据（必须是数据库查询结果）
     * @return 分页数据
     */
    public static <T> PageResponse<T> pack(List<T> list) {
        PageResponse<T> response = new PageResponse<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        response.setPages(pageInfo.getPages());
        response.setList(list);
        response.setTotal(pageInfo.getTotal());
        return response;
    }

    /**
     * 封装通用分页数据
     * <p>当list为数据库查询结果转换后的集合时使用该方法封装分页数据</p>
     *
     * @param list 查询结果
     * @param page 分页配置
     * @param <T>  返回结果类型
     * @return 分页数据
     */
    public static <T> PageResponse<T> pack(List<T> list, Page<T> page) {
        PageResponse<T> response = pack(list);
        if (Objects.nonNull(page)) {
            response.setTotal(page.getTotal());
            response.setPages(page.getPages());
        }
        return response;
    }

    /**
     * 封装通用分页数据
     *
     * @param page 分页配置
     * @param <T>  返回结果类型
     * @return 分页数据
     */
    public static <T> PageResponse<T> pack(Page<T> page) {
        PageResponse<T> response = new PageResponse<>();
        response.setList(page.getResult());
        response.setPages(page.getPages());
        response.setTotal(page.getTotal());
        return response;
    }

    /**
     * 封装通用分页数据
     * <p>需要逻辑分页处理时使用该方法封装分页数据</p>
     *
     * @param list  查询结果（）
     * @param total 总数据条数
     * @param pages 总页数
     * @return 分页数据
     */
    public static <T> PageResponse<T> pack(List<T> list, long total, int pages) {
        PageResponse<T> response = pack(list);
        response.setTotal(total);
        response.setPages(pages);
        return response;
    }
}
