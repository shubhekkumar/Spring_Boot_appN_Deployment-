module "network" {

  source = "./modules/network"

  project_name = var.project_name
  environment  = var.environment

}

module "security" {

  source = "./modules/security"

  project_name = var.project_name

  environment = var.environment

  vpc_id = module.network.vpc_id

}
