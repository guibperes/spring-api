package guizao.aula.api.product;

import org.springframework.web.bind.annotation.RequestMapping;

import guizao.aula.base.BaseController;
import guizao.aula.config.RestConfig;

@RestConfig
@RequestMapping("/product")
public class ProductController extends BaseController<Product, ProductRepository, ProductService> {
}
